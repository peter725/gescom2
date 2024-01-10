package es.consumo.junta_arbitral.commons.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import es.consumo.junta_arbitral.commons.constants.ApiEndpoints;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * APLICACION TULSA
 * MINISTERIO DE SANIDAD Y CONSUMO
 * AGENCIA ESPANIOLA DE SEGURIDAD ALIMENTARIA Y NUTRICION
 * <p>
 * Aplicacion para la manipulacion de los elementos, modulos y ambitos
 * de las distintas comunidades autonomas, provincias, paises u otros,
 * en conjunto con la EFSA.
 * <p>
 * <p>
 *
 * @author SERIKAT
 * @version 27/06/2022 0.0.1
 */
@Configuration
public class AppConfig {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setContextPath(ApiEndpoints.CONTEXT);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    //    @Bean
//    public Jackson2ObjectMapperBuilder jacksonBuilder() {
//        Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
//        b.modulesToInstall(Hibernate5Module.class);
//        return b;
//    }
    @Bean
    public MappingJackson2HttpMessageConverter mappingjackson2httpmessageconverter() {
        MappingJackson2HttpMessageConverter jsonconverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectmapper = jsonconverter.getObjectMapper();
        objectmapper.registerModule(new Hibernate5Module());


        return jsonconverter;
    }
}