package upskill.casestudy.mentorService;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Mentor Service REST APIs",
		description = "Mentor Service REST APIs Documentation",
		version = "v1.0",
		contact = @Contact(
				name = "Kamini",
				email = "kamini@gmail.com",
				url = "https://www.google.com/"),
		license = @License(
				name = "Apache 2.0",
				url = "https://www.google.com/"
		)
),
		externalDocs = @ExternalDocumentation(
				description = "Mentor-service Doc"
		)
)
public class MentorsServiceApplication {



	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MentorsServiceApplication.class, args);
	}

}
