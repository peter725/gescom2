package es.consumo.gescom.jwt.rest.auth.logic.security;

import es.consumo.gescom.jwt.rest.user.db.entity.LoginEntity;
import lombok.NonNull;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {


    private final CheckTokenEndpoint checkTokenEndpoint;
    private final UserDetailsService userDetailsService;

    @Autowired
    public JwtAuthorizationFilter(CheckTokenEndpoint checkTokenEndpoint,
                                  UserDetailsService userDetailsService) {
        this.checkTokenEndpoint = checkTokenEndpoint;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Se valida con null para no pisar el filtro que usa spring para la authorizacion basica
        if (Objects.isNull(authentication)) {
            String autorization = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION)).orElse("");

            autorization = autorization.replace("Bearer ", "");
            final Map<String, ?> map = checkTokenEndpoint.checkToken(autorization);
            final LoginEntity entity = (LoginEntity) userDetailsService.loadUserByUsername((String) map.get("user_name"));
            Hibernate.initialize(entity);
            final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    entity, null, new ArrayList<>());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }


}