package com.capg.batchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Batch Service REST APIs", 
		description = "Batch Service REST APIs Documentation", 
		version = "v1.0", 
		contact = @Contact(
				name = "Pragathi", 
				email = "pragathi@gmail.com", 
				url = "https://www.google.com/"),
		license = @License(
				name = "Apache 2.0",
				url = "https://www.google.com/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Batch-service Doc"
				)
)
public class BatchServiceApplication {

	@Bean
	public WebClient webClient() {
		return WebClient.builder().build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BatchServiceApplication.class, args);
	}

}
