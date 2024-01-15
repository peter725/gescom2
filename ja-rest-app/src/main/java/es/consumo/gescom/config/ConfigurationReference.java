package es.consumo.gescom.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"es.consumo.gescom.modules", "es.consumo.gescom.config"})
@EnableJpaRepositories("es.consumo.gescom.modules")
@EntityScan(basePackages = "es.consumo.gescom.modules")
public class ConfigurationReference {
}
