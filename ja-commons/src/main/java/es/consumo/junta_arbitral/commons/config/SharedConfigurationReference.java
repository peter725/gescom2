package es.consumo.junta_arbitral.commons.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("es.consumo.junta_arbitral.commons")
@EnableJpaRepositories("es.consumo.junta_arbitral.commons.db.repository")
@EntityScan(basePackages = "es.consumo.junta_arbitral.commons.db.entity")
public class SharedConfigurationReference {
}
