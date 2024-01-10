package es.consumo.junta_arbitral.jwt.rest.auth.logic.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("es.consumo.junta_arbitral.jwt.rest")
@EnableJpaRepositories("es.consumo.junta_arbitral.jwt.rest.user.db.repository")
@EntityScan(basePackages = "es.consumo.junta_arbitral.jwt.rest.user.db.entity")
public class ConfigurationReference {
}
