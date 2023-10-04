package es.dgc.gesco.service.util;

import es.dgc.gesco.service.repository.UsuarioRepository;
import es.dgc.gesco.service.service.UsuarioService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestFactory {

    @Bean
    public UsuarioService usuarioService(UsuarioRepository usuarioRepository){
        return new UsuarioService(usuarioRepository);
    }
}
