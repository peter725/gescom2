package es.dgc.gesco.service.service;

import es.dgc.gesco.service.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    @Autowired
    final private UsuarioRepository usuarioRepository;


    public void add(String mensaje){
        System.out.println();
    }

}
