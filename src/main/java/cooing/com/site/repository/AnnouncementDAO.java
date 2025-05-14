package cooing.com.site.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import cooing.com.site.domain.AnnouncementImageVO;
import cooing.com.site.domain.AnnouncementVO;
import cooing.com.site.mapper.AnnouncementMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AnnouncementDAO {
    private final AnnouncementMapper announcementMapper;

    // 공지등록
    public void insert(AnnouncementVO announcementVO){
        announcementMapper.insert(announcementVO);
    }

    // 공지리스트
    public List<AnnouncementVO> selectAll() {
        return announcementMapper.selectAll();
    }

    // 공지 상세 조회
    public AnnouncementVO findById(Long id) {
        AnnouncementVO vo = announcementMapper.findById(id);
        if (vo != null) {
            vo.setImages(announcementMapper.getImagesByAnnouncementId(id));
        }
        return vo;
    }
    

    // 공지 수정
    public void update(AnnouncementVO announcementVO) {
        announcementMapper.update(announcementVO);
    }

    // 공지 삭제
    public void delete(Long id) {
        announcementMapper.delete(id);
    }

    // --- 이미지 관련 ---

    // 이미지 등록
    public void insertImage(AnnouncementImageVO image) {
        announcementMapper.insertImage(image);
    }

    // 특정 공지의 이미지 리스트 조회
    public List<AnnouncementImageVO> getImagesByAnnouncementId(Long announcementId) {
        return announcementMapper.getImagesByAnnouncementId(announcementId);
    }

    // 이미지 삭제
    public void deleteImagesById(Long id) {
        announcementMapper.deleteImageById(id);
    }
    
}
