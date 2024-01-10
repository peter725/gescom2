package es.consumo.junta_arbitral.commons.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@SecurityScheme(
		name = "Authorization",
		scheme = "bearer",
		bearerFormat = "JWT",
		type = SecuritySchemeType.HTTP,
		in = SecuritySchemeIn.HEADER
)
@Profile("swagger")
public class SwaggerConfig {

	@Value("${openapi.swagger.version}")
	private String version;
	@Value("${openapi.swagger.title}")
	private String title;
	@Value("${openapi.swagger.description}")
	private String description;
	@Value("${openapi.swagger.licence}")
	private String licence;
	@Value("${openapi.swagger.licenceUrl}")
	private String licenceUrl;

	@Bean
	public OpenAPI docConfiguration() {
		Info apiInfo = new Info()
				.title(title)
				.description(description)
				.version(version)
				.contact(buildContact())
				.license(buildLicence());

		SecurityRequirement globalSecurity = new SecurityRequirement().addList("Authorization");

		return new OpenAPI()
				.info(apiInfo)
				.addSecurityItem(globalSecurity);
	}

	private License buildLicence() {
		return new License()
				.name(licence)
				.url(licenceUrl);
	}

	private Contact buildContact() {
		return new Contact()
				.name("AESAN")
				.url(licenceUrl)
				.email("PDTE");
	}
}
