package es.dgc.gesco.service.service;

import es.dgc.gesco.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    final private UserRepository userRepository;


    public void add(String mensaje){
        System.out.println();
    }

}
