package es.dgc.gesco.service.config;

import es.dgc.gesco.service.service.StringEncrypterService;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

//@PropertySource("file:/documentospro/properties/GESCO/jdbc.properties")
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
        System.out.println("username: " + env.getProperty("jdbc.username"));
        System.out.println("password: " + password);
        source.setPassword(password);
        return source;
    }
}
