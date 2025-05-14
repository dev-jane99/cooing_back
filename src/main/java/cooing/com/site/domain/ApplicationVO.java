package cooing.com.site.domain;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ApplicationVO {
    private Long id;
    private Long announcementId;
    private String announcementTitle;
    private String applicantName;
    private String mobile;
    private int totalApplicants;        
    private String preferredDate;        
    private String preferredTime;
    private String note;                 
    private String status;               
    private String paymentMethod;        
    private Boolean isPaid;              
    private String paymentTransactionId; 
    private Timestamp applicationDate;
}


