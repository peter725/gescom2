package es.consumo.gescom.jwt.rest.auth.logic.security;

import es.consumo.gescom.jwt.rest.user.db.entity.LoginEntity;
import lombok.NonNull;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);



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
            // Log del encabezado de autorización extraído
            logger.info("Authorization Header: {}", autorization);

            autorization = autorization.replace("Bearer ", "");
            // Log del token después de quitar "Bearer "
            logger.info("Token: {}", autorization);

            final Map<String, ?> map = checkTokenEndpoint.checkToken(autorization);
            // Log de la respuesta de checkToken
            logger.info("CheckToken response: {}", map);

            final LoginEntity entity = (LoginEntity) userDetailsService.loadUserByUsername((String) map.get("user_name"));
            Hibernate.initialize(entity);
            final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    entity, null, entity.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }


}