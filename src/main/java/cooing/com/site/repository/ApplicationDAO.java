package cooing.com.site.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import cooing.com.site.domain.ApplicationVO;
import cooing.com.site.mapper.ApplicationMapper;

@Repository
public class ApplicationDAO {

    private final ApplicationMapper applicationMapper;

    public ApplicationDAO(ApplicationMapper applicationMapper) {
        this.applicationMapper = applicationMapper;
    }

    public List<ApplicationVO> findAll() {
        return applicationMapper.findAll();
    }

    public Optional<ApplicationVO> findById(Long id) {
        return Optional.ofNullable(applicationMapper.findById(id));
    }

    public boolean isTimeSlotReserved(Long announcementId, LocalDate preferredDate, String preferredTime) {
        int count = applicationMapper.isTimeSlotReserved(announcementId, preferredDate, preferredTime);
        return count > 0;
    }

    public void insert(ApplicationVO application) {
        applicationMapper.insert(application);
    }

    public void update(ApplicationVO applicationVO) {
        applicationMapper.update(applicationVO);
    }

    public void deleteById(Long id) {
        applicationMapper.deleteById(id);
    }

    public void deleteByIds(List<Long> ids) {
        applicationMapper.deleteByIds(ids);
    }

    public List<ApplicationVO> findByAnnouncementId(Long announcementId) {
        return applicationMapper.findByAnnouncementId(announcementId);
    }
}
