package es.consumo.gescom.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RestTemplateConfig {

    private final Environment environment;

    public RestTemplateConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean("authClient")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        final String context = isLocal() ? "" : "/jjaa-auth-app";
        log.info("restTemplate - authClient context:[{}]", context);
        return builder
                .basicAuthentication("JJAA", "JJAA")
                .additionalInterceptors((HttpRequest request, byte[] body, ClientHttpRequestExecution execution) -> {
                    final HttpHeaders headers = request.getHeaders();
                    if (request.getURI().toString().contains("/oauth/")) {
                        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    }
                    return execution.execute(request, body);
                })
                .messageConverters(new FormHttpMessageConverter(), new MappingJackson2HttpMessageConverter())
                .rootUri(environment.getProperty("spring.base.url") + context)
                .build();
    }

    private boolean isLocal() {
        for (String profile : environment.getActiveProfiles()) {
            if (profile.equals("local")) {
                return true;
            }
        }
        return false;
    }
}
