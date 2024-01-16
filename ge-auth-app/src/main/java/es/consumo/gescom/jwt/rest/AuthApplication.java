package es.consumo.gescom.jwt.rest;

import es.consumo.gescom.commons.config.SharedConfigurationReference;
import es.consumo.gescom.jwt.rest.auth.logic.config.ConfigurationReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import({SharedConfigurationReference.class, ConfigurationReference.class})
public class AuthApplication extends SpringBootServletInitializer  {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
