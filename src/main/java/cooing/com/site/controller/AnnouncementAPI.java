package cooing.com.site.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import cooing.com.site.domain.AnnouncementImageVO;
import cooing.com.site.domain.AnnouncementVO;
import cooing.com.site.service.AnnouncementService;
import io.swagger.v3.oas.annotations.Operation;
// import cooing.com.site.util.ImageUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.File;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/announcements")
@Slf4j
public class AnnouncementAPI {

    private final AnnouncementService announcementService;
    // private final ImageUploader imageUploader;

    // 전체 공지 조회
    @GetMapping
    public List<AnnouncementVO> getAll() {
        return announcementService.getAllAnnouncements();
    }

    // 공지 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementVO> getById(@PathVariable Long id) {
        AnnouncementVO announcement = announcementService.getAnnouncementById(id);
        if (announcement != null) {
            return ResponseEntity.ok(announcement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    // 공지 등록
    @Operation(summary = "공지 등록", description = "새로운 공지를 등록합니다.")
@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<Long> create(
    @RequestPart("data") AnnouncementVO announcementVO,
    @RequestPart(value = "images", required = false) List<MultipartFile> images
) throws IOException
{
        announcementService.createAnnouncement(announcementVO);

        if (images != null && !images.isEmpty()) {
            String uploadDir = new ClassPathResource("static/images").getFile().getAbsolutePath();
            for (MultipartFile file : images) {
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                File dest = new File(uploadDir + File.separator + fileName);
                file.transferTo(dest);

                AnnouncementImageVO imageVO = new AnnouncementImageVO();
                imageVO.setAnnouncementId(announcementVO.getId());
                imageVO.setImageUrl("/images/" + fileName);
                announcementService.addAnnouncementImage(imageVO);
            }
        }

        return ResponseEntity.ok(announcementVO.getId());
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<List<AnnouncementImageVO>> getImages(@PathVariable Long id) {
        List<AnnouncementImageVO> images = announcementService.getImagesByAnnouncementId(id);
        return ResponseEntity.ok(images);
    }

    // 공지 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AnnouncementVO announcementVO) {
        announcementVO.setId(id);
        announcementService.updateAnnouncement(announcementVO);
        return ResponseEntity.ok("공지 수정 완료");
    }

    // 공지 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.ok("공지 삭제 완료");
    }


    @PostMapping("/{id}/images")
    public ResponseEntity<?> uploadImages(
            @PathVariable Long id, 
            @RequestParam(value = "images", required = false) List<MultipartFile> images
    ) throws IOException {
        if (images == null || images.isEmpty()) {
            return ResponseEntity.ok("이미지 없음");
        }

        String uploadDir = new ClassPathResource("static/images").getFile().getAbsolutePath();

        for (MultipartFile file : images) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(uploadDir + File.separator + fileName);
            file.transferTo(dest);

            AnnouncementImageVO imageVO = new AnnouncementImageVO();
            imageVO.setAnnouncementId(id);
            imageVO.setImageUrl("/images/" + fileName);
            announcementService.addAnnouncementImage(imageVO);
        }

        return ResponseEntity.ok("이미지 업로드 완료");
    } 

    // 이미지 단일 삭제
    @DeleteMapping("/images/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable Long imageId) {
        announcementService.deleteImageById(imageId);
        return ResponseEntity.ok("이미지 삭제 완료");
    }

    // 공지 일괄 삭제
@DeleteMapping("/bulk-delete")
public ResponseEntity<?> bulkDelete(@RequestBody List<Long> ids) {
    for (Long id : ids) {
        announcementService.deleteAnnouncement(id); // 기존 삭제 로직 활용
    }
    return ResponseEntity.ok("일괄 삭제 완료");
}

}
