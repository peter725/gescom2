package es.consumo.gescom.modules.security;

import es.consumo.gescom.modules.role.model.entity.RoleHasModuleEntity;
import es.consumo.gescom.modules.role.repository.RoleHasModuleRepository;
import es.consumo.gescom.modules.users.model.entity.LoginEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AuthenticationManagerJwt implements AuthenticationManager {

    private final RestTemplate restTemplate;
    private final UserDetailsService userDetailsService;
    private final RoleHasModuleRepository roleHasModuleRepository;

    public AuthenticationManagerJwt(@Qualifier("authClient") RestTemplate restTemplate,
                                    UserDetailsService userDetailsService,
                                    RoleHasModuleRepository roleHasModuleRepository) {
        this.restTemplate = restTemplate;
        this.userDetailsService = userDetailsService;
        this.roleHasModuleRepository = roleHasModuleRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        VerifyToken verifyToken = mapVerifyToken((String) authentication.getCredentials());
        return mapAuth(verifyToken);
    }

    private VerifyToken mapVerifyToken(String token) {

        try {
            MultiValueMap<String, Object> request = new LinkedMultiValueMap<>();
            request.put("token", List.of(token));
            return restTemplate.postForObject("/oauth/check_token", request, VerifyToken.class);
        } catch (Exception ex) {
            throw ex;
        }
    }

    private Authentication mapAuth(VerifyToken verifyToken) {
        LoginEntity loginEntity = (LoginEntity) userDetailsService.loadUserByUsername(verifyToken.getUserName());

        ArrayList<String> roles = verifyToken.getRoleName();
        Set<GrantedAuthority> authorities = getAuthority(roles);
        return new UsernamePasswordAuthenticationToken(loginEntity, null, authorities);
    }

    private Map<String, Set<String>> getRoles(LoginEntity loginEntity) {

        final Set<RoleHasModuleEntity.FullAuthoritiesProjection> roles =
                this.roleHasModuleRepository.findAllRolesByLoginId(loginEntity.getId());

        return roles.stream()
            .collect(Collectors.groupingBy(RoleHasModuleEntity.FullAuthoritiesProjection::getRoleName,
            Collectors.mapping(RoleHasModuleEntity.FullAuthoritiesProjection::getRoleName, Collectors.toSet())));

    }

    private Set<GrantedAuthority> getAuthority(ArrayList<String> roles) {
        return roles.stream()
                .map(role -> role.replace("[", "").replace("]", "").trim())
                .map(SimpleGrantedAuthority::new)  // Directamente crea SimpleGrantedAuthority con el rol
                .collect(Collectors.toSet());
    }

}
