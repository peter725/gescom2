package es.consumo.gescom.commons.config;

import es.consumo.gescom.commons.service.StringEncrypterService;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

@Profile("jndi")
@Configuration
public class JNDIConfig {
    private final StringEncrypterService encrypterService;

    public JNDIConfig(StringEncrypterService encrypterService) {
        this.encrypterService = encrypterService;
    }

   @Bean
    public DataSource dataSource(Environment env) {
        JndiDataSourceLookup lookup = new JndiDataSourceLookup();
        BasicDataSource source = (BasicDataSource) lookup.getDataSource(env.getProperty("jdbc.jndiName"));
        String password = encrypterService.decrypt(env.getProperty("jdbc.password"));
        source.setUsername(env.getProperty("jdbc.username"));
        source.setPassword(password);
        return source;
    }
}
