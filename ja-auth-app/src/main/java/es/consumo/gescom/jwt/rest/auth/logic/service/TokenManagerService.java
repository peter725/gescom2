package es.consumo.gescom.jwt.rest.auth.logic.service;

import es.consumo.gescom.jwt.rest.user.db.entity.LoginEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface TokenManagerService {

    OAuth2AccessToken getAccessToken(String dni);

    OAuth2AccessToken getAccessToken(LoginEntity entity);
}
