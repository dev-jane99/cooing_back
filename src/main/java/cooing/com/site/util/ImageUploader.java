package cooing.com.site.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUploader {

    // 실제 서버 파일 저장 위치 (예: /Users/niki/projects/site/uploads)
    private static final String UPLOAD_DIR = "/Users/jane/atelier/cooing/images";

    // 웹에서 접근 가능한 URL 경로 (예: /uploads/filename.jpg)
    private static final String ACCESS_URL_PREFIX = "/images/";

    public String upload(MultipartFile file) throws IOException {
        // 고유한 파일명 생성 (중복 방지)
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // 저장 경로 객체
        File dest = new File(UPLOAD_DIR, fileName);
        file.transferTo(dest);

        // 클라이언트에서 접근할 수 있는 경로 반환
        return ACCESS_URL_PREFIX + fileName;
    }
}
