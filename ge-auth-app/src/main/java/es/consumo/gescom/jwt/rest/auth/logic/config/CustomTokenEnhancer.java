package es.consumo.gescom.jwt.rest.auth.logic.config;

import es.consumo.gescom.jwt.rest.user.db.entity.LoginEntity;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.core.GrantedAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomTokenEnhancer implements TokenEnhancer {

    private static final Logger logger = LoggerFactory.getLogger(CustomTokenEnhancer.class);

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();

        LoginEntity user = (LoginEntity) authentication.getPrincipal();

        // Log roles for debugging
        logger.info("Adding roles to token for user: {}", user.getUsername());
        user.getAuthorities().forEach(authority -> logger.info("Role: {}", authority.getAuthority()));

        // Agregar los roles al token
        additionalInfo.put("role", user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
