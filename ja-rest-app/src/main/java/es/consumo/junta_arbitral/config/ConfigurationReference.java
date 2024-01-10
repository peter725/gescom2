package es.consumo.junta_arbitral.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"es.consumo.junta_arbitral.modules", "es.consumo.junta_arbitral.config"})
@EnableJpaRepositories("es.consumo.junta_arbitral.modules")
@EntityScan(basePackages = "es.consumo.junta_arbitral.modules")
public class ConfigurationReference {
}
