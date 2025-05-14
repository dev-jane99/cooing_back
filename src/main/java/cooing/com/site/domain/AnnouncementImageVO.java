package cooing.com.site.domain;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class AnnouncementImageVO {
    private Long id;
    private Long announcementId;
    private String imageUrl;
}

