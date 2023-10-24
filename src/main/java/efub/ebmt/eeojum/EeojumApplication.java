package efub.ebmt.eeojum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EeojumApplication {

	public static void main(String[] args) {
		SpringApplication.run(EeojumApplication.class, args);
	}

}
