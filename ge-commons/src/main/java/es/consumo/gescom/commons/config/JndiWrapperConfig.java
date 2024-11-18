package es.consumo.gescom.commons.config;

import es.consumo.gescom.commons.service.StringEncrypterService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Profile("jndi-jdbc")
@Configuration
@Slf4j
public class JndiWrapperConfig extends JNDIConfig {

    public JndiWrapperConfig(StringEncrypterService encrypterService) {
        super(encrypterService);
    }

    @Primary
    @Bean
    @Override
    public DataSource dataSource(Environment env) {
        DataSource response = DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(getDataSource(env).getUrl())
                .username(env.getProperty("jdbc.username"))
                .password(getPassword(env))
                .build();

        /*log.info("******************{}******************************", getPasswordtest(""));*/
        return response;


    }

}