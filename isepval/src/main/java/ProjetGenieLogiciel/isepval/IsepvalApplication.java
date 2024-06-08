package ProjetGenieLogiciel.isepval;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class IsepvalApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsepvalApplication.class, args);
	}

}
