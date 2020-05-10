package com.openclassrooms.springdatarest.petitionservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(title = "Petition Service", description = "An API for democracy", version = "1.0")
)
@SpringBootApplication
public class PetitionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetitionServiceApplication.class, args);
	}
}
