package es.consumo.junta_arbitral.jwt.rest.auth.logic.service.impl;

import es.consumo.junta_arbitral.jwt.rest.auth.logic.service.TokenManagerService;
import es.consumo.junta_arbitral.jwt.rest.user.db.entity.LoginEntity;
import org.hibernate.Hibernate;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenManagerServiceImpl implements TokenManagerService {

    private final AuthorizationServerTokenServices tokenServices;
    private final UserDetailsService userDetailsService;
    private final Environment environment;

    private final ClientDetailsService clientDetailsService;

    public TokenManagerServiceImpl(AuthorizationServerTokenServices tokenServices, UserDetailsService userDetailsService,
                                   Environment environment, ClientDetailsService clientDetailsService) {
        this.tokenServices = tokenServices;
        this.userDetailsService = userDetailsService;
        this.environment = environment;
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    public OAuth2AccessToken getAccessToken(String dni) {
        final LoginEntity entity = (LoginEntity) userDetailsService.loadUserByUsername(dni);
        Hibernate.initialize(entity);
    return getAccessToken(entity);
    }
    @Override
    public OAuth2AccessToken getAccessToken(LoginEntity entity) {

        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                entity, null, new ArrayList<>());
        String clientId = environment.getProperty("config.security.oauth.client.id");
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("client_id", clientDetails.getClientId());
        parameters.put("grant_type", "authorization_code");

        OAuth2Request request = new OAuth2Request(parameters, clientId, new ArrayList<>(), true,
                clientDetails.getScope(), clientDetails.getResourceIds(), null, null,
                null);
        OAuth2Authentication authentication = new OAuth2Authentication(request, authenticationToken);
        return tokenServices.createAccessToken(authentication);
    }
}
