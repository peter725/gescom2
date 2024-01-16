package es.consumo.gescom.commons.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class AppInfo {
    @Value("${spring.application.name}")
    private String name;
    @Value("${spring.application.version}")
    private String version;
    @Value("${spring.application.timestamp}")
    private String timestamp;
    @Value("${spring.profiles.active}")
    private String profile;
    @Value("${weblogic.Name:manual}")
    private String executionNode;
}
