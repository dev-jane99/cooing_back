package cooing.com.site.mapper;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cooing.com.site.domain.ApplicationVO;

@Mapper
public interface ApplicationMapper {

    // 전체 신청서 조회
    List<ApplicationVO> findAll();

    // 단건 조회
    ApplicationVO findById(Long id);

    // 특정 날짜/시간에 이미 예약이 있는지 확인
    int isTimeSlotReserved(
        @Param("announcementId") Long announcementId,
        @Param("preferredDate") LocalDate preferredDate,
        @Param("preferredTime") String preferredTime
    );
    

    // 신청서 등록
    void insert(ApplicationVO application);

    // 신청서 업데이트
    void update(ApplicationVO applicationVO);

    // 신청서 삭제
    void deleteById(Long id);

    // 여러 건 삭제
    void deleteByIds(@Param("ids") List<Long> ids);

    // 예약된 시간 목록 조회
    List<ApplicationVO> findByAnnouncementId(Long announcementId);
}
