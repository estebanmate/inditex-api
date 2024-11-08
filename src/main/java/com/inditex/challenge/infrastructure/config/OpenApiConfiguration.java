package com.inditex.challenge.infrastructure.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(apiInfo());
	}

	private Info apiInfo() {
		return new Info().title("Prices REST API").description("\"Prices API for Inditex Challenge\"")
				.version("1.0.0").contact(apiContact()).license(apiLicence());
	}

	private License apiLicence() {
		return new License().name("Apache License Version 2.0").url("https://www.apache.org/licenses/LICENSE-2.0\"");
	}

	private Contact apiContact() {
        return new Contact()
                .name("Esteban Martín-Tembleque Poves")
                .email("estebanmate@gmail.com");
    }
}
