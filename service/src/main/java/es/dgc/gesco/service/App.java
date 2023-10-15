package es.dgc.gesco.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "es.dgc.gesco.service.repository")
@EntityScan(basePackages = "es.dgc.gesco.model.modules.user.db.entity")
@ComponentScan(basePackages = {
        "es.dgc.gesco.model.modules"
})
public class App 
{
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
