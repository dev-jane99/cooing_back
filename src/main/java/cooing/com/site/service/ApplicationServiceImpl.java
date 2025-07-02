package cooing.com.site.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cooing.com.site.domain.ApplicationVO;
import cooing.com.site.repository.ApplicationDAO;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationDAO applicationDAO;

    public ApplicationServiceImpl(ApplicationDAO applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    @Override
    public List<ApplicationVO> getAllApplications() {
        return applicationDAO.findAll();
    }

    @Override
    public Optional<ApplicationVO> getApplicationById(Long id) {
        return applicationDAO.findById(id);
    }

    @Override
    public boolean isTimeSlotReserved(Long announcementId, LocalDate preferredDate, String preferredTime) {
        return applicationDAO.isTimeSlotReserved(announcementId, preferredDate, preferredTime);
    }

    @Override
    public void createApplication(ApplicationVO application) {
        applicationDAO.insert(application);
    }

    @Override
    public void updateApplication(ApplicationVO applicationVO) {
        applicationDAO.update(applicationVO);
    }

    @Override
    public void deleteApplication(Long id) {
        applicationDAO.deleteById(id);
    }

    @Override
    public void deleteApplications(List<Long> ids) {
        applicationDAO.deleteByIds(ids);
    }

    @Override
    public List<ApplicationVO> findByAnnouncementId(Long announcementId) {
        return applicationDAO.findByAnnouncementId(announcementId);
    }
}
