package cooing.com.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({
	"cooing.com.site.repository", 
	"cooing.com.site.mapper" 
  })
public class SiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
	}
	

}
