package cooing.com.site.service;

import java.util.List;
import cooing.com.site.domain.AnnouncementImageVO;
import cooing.com.site.domain.AnnouncementVO;

public interface AnnouncementService {
    public Long createAnnouncement(AnnouncementVO announcementVO);

    public List<AnnouncementVO> getAllAnnouncements();

    public AnnouncementVO getAnnouncementById(Long id);

    public void updateAnnouncement(AnnouncementVO announcementVO);

    public void deleteAnnouncement(Long id);

    public void addAnnouncementImage(AnnouncementImageVO image);

    public List<AnnouncementImageVO> getImagesByAnnouncementId(Long announcementId);

    public void deleteImageById(Long id);
}
