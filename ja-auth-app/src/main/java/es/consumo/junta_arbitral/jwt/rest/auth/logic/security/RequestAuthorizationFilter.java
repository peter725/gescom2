package es.consumo.junta_arbitral.jwt.rest.auth.logic.security;

import lombok.NonNull;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

@Component
@Order(2)
public class RequestAuthorizationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
            throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Basic ")) {
            byte[] base64 = Base64.getDecoder().decode(requestTokenHeader.substring(6));
            String[] data = new String(base64).split(":");

            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(
                    data[0], data[1], new ArrayList<>());

            userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(userAuth);
        }
        chain.doFilter(request, response);
    }
    // other methods
}