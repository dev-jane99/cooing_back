package cooing.com.site.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class AnnouncementVO {
    private Long id;
    private String title;
    private String detail;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
    private List<AnnouncementImageVO> images;
    private String availableTimes; // ["10:00", "13:00", "15:00"] 등
    private int maxCapacity;             // 총 신청 가능 인원 (optional)
    private int price;                   // 단가 (원 단위)
    private String paymentMethods; // ["카카오페이", "네이버페이", "신용카드", "계좌이체"]
}
