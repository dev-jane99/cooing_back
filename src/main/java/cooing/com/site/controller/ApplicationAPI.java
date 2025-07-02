package cooing.com.site.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cooing.com.site.domain.ApplicationVO;
import cooing.com.site.service.ApplicationService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000") // 개발 중 프론트 연동용
@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationAPI {

    private final ApplicationService applicationService;

    // 전체 신청서 조회
    @GetMapping
    public List<ApplicationVO> getApplications() {
        return applicationService.getAllApplications();
    }

    // 신청서 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ApplicationVO> getApplication(@PathVariable Long id) {
        return applicationService.getApplicationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 신청서 등록
    @PostMapping
    public ResponseEntity<String> apply(@RequestBody ApplicationVO applicationVO) {
        // 특정 날짜와 시간대가 이미 예약되었는지 확인
        boolean isReserved = applicationService.isTimeSlotReserved(
            applicationVO.getAnnouncementId(),
            applicationVO.getPreferredDate(),
            applicationVO.getPreferredTime()
            );
    
        // 예약이 마감되었을 경우
        if (isReserved) {
            return ResponseEntity
                .badRequest()
                .body("해당 날짜와 시간은 이미 마감되었습니다. 다른 시간을 선택해주세요.");
        }

        // 신청서 등록
        applicationService.createApplication(applicationVO);
        return ResponseEntity.ok("신청이 완료되었습니다!");
    }

    // 신청서 단건 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.ok("신청서가 삭제되었습니다!");
    }

    // 신청서 여러 건 삭제
    @DeleteMapping("/bulk-delete")
    public ResponseEntity<String> bulkDelete(@RequestBody List<Long> ids) {
        applicationService.deleteApplications(ids);
        return ResponseEntity.ok("선택한 신청서가 삭제되었습니다!");
    }

    // 예약된 시간 목록 반환 (비활성화, 활성화 확인용)
    @GetMapping("/reserved-slots")
    public List<Map<String, String>> getReservedSlots(@RequestParam Long announcementId) {
        return applicationService.findByAnnouncementId(announcementId).stream()
                .map(app -> Map.of(
                    "date", app.getPreferredDate() != null ? app.getPreferredDate().toString() : "",
                    "time", app.getPreferredTime()
                ))
                .collect(Collectors.toList());
    }    
}
