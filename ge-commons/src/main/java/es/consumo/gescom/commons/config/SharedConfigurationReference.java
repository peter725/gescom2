package es.consumo.gescom.commons.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("es.consumo.gescom.commons")
@EnableJpaRepositories("es.consumo.gescom.commons.db.repository")
@EntityScan(basePackages = "es.consumo.gescom.commons.db.entity")
public class SharedConfigurationReference {
}
