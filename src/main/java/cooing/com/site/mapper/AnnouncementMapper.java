package cooing.com.site.mapper;

import cooing.com.site.domain.AnnouncementImageVO;
import cooing.com.site.domain.AnnouncementVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    public List<AnnouncementVO> selectAll();
    public AnnouncementVO findById(Long id);
    public void insert(AnnouncementVO announcementVO);
    public void update(AnnouncementVO announcementVO);
    public void delete(Long id);
    // --- 이미지 ---
    public void insertImage(AnnouncementImageVO image);
    public List<AnnouncementImageVO> getImagesByAnnouncementId(Long announcementId);
    public void deleteImageById(Long id);

}


