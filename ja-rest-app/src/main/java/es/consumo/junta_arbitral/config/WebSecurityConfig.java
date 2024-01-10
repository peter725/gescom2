package es.consumo.junta_arbitral.config;

import es.consumo.junta_arbitral.commons.controller.advice.FilterChainExceptionHandler;
import es.consumo.junta_arbitral.modules.security.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.http.HttpMethod.OPTIONS;

@Configuration("securityConfig")
@EnableWebSecurity
public class WebSecurityConfig {
    private final JwtAuthorizationFilter authorizationFilter;
    private final FilterChainExceptionHandler chainExceptionFilter;

    public WebSecurityConfig(JwtAuthorizationFilter authorizationFilter, FilterChainExceptionHandler chainExceptionFilter) {
        this.authorizationFilter = authorizationFilter;
        this.chainExceptionFilter = chainExceptionFilter;
    }

    public String[] getPublicEndpoints() {
        return new String[]{
                "/",
                "/api-docs/**",
                "/swagger-ui",
                "/swagger-ui/**",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/**",
                "/swagger-ui.html",
                "/webjars/**"
        };
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("*"));
                    configuration.setAllowedMethods(List.of("*"));
                    configuration.setAllowedHeaders(List.of("*"));
                    return configuration;
                }))
                .authorizeHttpRequests(requests -> requests
                        .antMatchers(OPTIONS, "/**").permitAll()
                        .antMatchers(getPublicEndpoints()).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(chainExceptionFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}