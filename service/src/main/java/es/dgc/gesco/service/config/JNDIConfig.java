package es.dgc.gesco.service.config;

import es.dgc.gesco.service.service.StringEncrypterService;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import lombok.extern.log4j.Log4j2;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Log4j2
@PropertySource("file:/documentospro/properties/GESCO/jdbc.properties")
@Profile("dev")
@Configuration
public class JNDIConfig {

    private final StringEncrypterService encrypterService;

    public JNDIConfig(StringEncrypterService encrypterService) {
        this.encrypterService = encrypterService;
    }

    @Bean(name = "dataSource", destroyMethod = "")
    public DataSource dataSource(Environment env) {
        try {
            JndiDataSourceLookup lookup = new JndiDataSourceLookup();
            BasicDataSource source = (BasicDataSource) lookup.getDataSource(env.getProperty("jdbc.jndiName"));
            String password = encrypterService.decrypt(env.getProperty("jdbc.password"));
            source.setUsername(env.getProperty("jdbc.username"));
            source.setPassword(password);
            return source;
        } catch (Exception e) {
            // Manejo para otras excepciones
            log.error("Error al configurar el DataSource", e);
            throw new RuntimeException("Error al configurar el DataSource", e);
        }
    }
}
