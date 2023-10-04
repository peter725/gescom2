package es.dgc.gesco.service.service;

import es.dgc.gesco.service.util.TestFactory;
import es.dgc.gesco.service.repository.AbstractGenericTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import(TestFactory.class)
public class UsuarioServiceTest extends AbstractGenericTest {

    @Autowired
    UsuarioService usuarioService;

    @Test
    public void addTest(){
        usuarioService.add("Mensaje");
    }

}
