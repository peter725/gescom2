package es.dgc.gesco.service.util;

import es.dgc.gesco.service.repository.UserRepository;
import es.dgc.gesco.service.service.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestFactory {

    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }
}
