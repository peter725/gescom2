package es.dgc.gesco.service.repository;

import static org.junit.jupiter.api.Assertions.*;

import es.dgc.gesco.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioRepositoryTest extends AbstractGenericTest  {

    @Autowired
    UsuarioRepository usuarioRepository;


    @Test
    public void saveTest(){
        List<Usuario> usuariosList = usuarioRepository.findAll();
        assertEquals(1,usuariosList.size());
    }


}
