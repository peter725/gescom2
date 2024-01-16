package es.consumo.gescom.commons.controller.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Component
public class FilterChainExceptionHandler extends OncePerRequestFilter {

    private final GlobalControllerExceptionHandler resolveException;

    private final ObjectMapper mapper;

    public FilterChainExceptionHandler(@Qualifier("globalControllerExceptionHandler") GlobalControllerExceptionHandler resolveException,
                                       ObjectMapper mapper) {
        this.resolveException = resolveException;
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            ResponseEntity<?> errorDTO = resolveException.handleException(e);
            response.setStatus(errorDTO.getStatusCode().value());
            response.setContentType("application/json");
            for (Map.Entry<String, List<String>> header : errorDTO.getHeaders().entrySet()) {
                response.setHeader(header.getKey(), String.join(",", header.getValue()));
            }
            //pass down the actual obj that exception handler normally send
            PrintWriter out = response.getWriter();
            out.print(mapper.writeValueAsString(errorDTO.getBody()));
            out.flush();
        }
    }
}