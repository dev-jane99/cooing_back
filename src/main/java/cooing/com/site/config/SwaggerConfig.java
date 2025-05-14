package cooing.com.site.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI cooingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cooing API 문서")
                        .description("공지 및 신청서 관련 API")
                        .version("v1.0"));
    }
}
