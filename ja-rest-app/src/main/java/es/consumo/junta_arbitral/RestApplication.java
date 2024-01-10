package es.consumo.junta_arbitral;


import es.consumo.junta_arbitral.commons.config.SharedConfigurationReference;
import es.consumo.junta_arbitral.config.ConfigurationReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
//@Import({SharedConfigurationReference.class, ConfigurationReference.class})
@EnableJpaAuditing
public class RestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}
