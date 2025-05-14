package cooing.com.site.service;

import java.util.List;
import java.util.Optional;

import cooing.com.site.domain.ApplicationVO;

public interface ApplicationService {

    List<ApplicationVO> getAllApplications();

    Optional<ApplicationVO> getApplicationById(Long id);

    boolean isTimeSlotReserved(Long announcementId, String preferredDate, String preferredTime);

    void createApplication(ApplicationVO application);

    void updateApplication(ApplicationVO applicationVO);

    void deleteApplication(Long id);

    void deleteApplications(List<Long> ids);

    List<ApplicationVO> findByAnnouncementId(Long announcementId);
}
