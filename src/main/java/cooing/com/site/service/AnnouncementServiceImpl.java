package cooing.com.site.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cooing.com.site.domain.AnnouncementImageVO;
import cooing.com.site.domain.AnnouncementVO;
import cooing.com.site.repository.AnnouncementDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementDAO announcementDAO;

    // 공지 등록
    @Override
    public Long createAnnouncement(AnnouncementVO announcementVO) {
        announcementDAO.insert(announcementVO);
        return announcementVO.getId(); 
    }

    // 공지 리스트 조회
    @Override
    public List<AnnouncementVO> getAllAnnouncements() {
        return announcementDAO.selectAll();
    }

    // 공지 상세 조회
    @Override
    public AnnouncementVO getAnnouncementById(Long id) {
        return announcementDAO.findById(id); // Optional 제거
    }
    

    // 공지 수정
    @Override
    public void updateAnnouncement(AnnouncementVO announcementVO) {
        announcementDAO.update(announcementVO);
    }

    // 공지 삭제
    @Override
    public void deleteAnnouncement(Long id) {
        List<AnnouncementImageVO> images = announcementDAO.getImagesByAnnouncementId(id);
        for (AnnouncementImageVO image : images) {
            announcementDAO.deleteImagesById(image.getId());
        }
        announcementDAO.delete(id);
    }

    // 이미지 등록
    @Override
    public void addAnnouncementImage(AnnouncementImageVO image) {
        announcementDAO.insertImage(image);
    }

    // 특정 공지의 이미지 리스트 조회
    @Override
    public List<AnnouncementImageVO> getImagesByAnnouncementId(Long announcementId) {
        return announcementDAO.getImagesByAnnouncementId(announcementId);
    }

    // 이미지 단건 삭제
    @Override
    public void deleteImageById(Long imageId) {
        announcementDAO.deleteImagesById(imageId);
    }
}
