package es.dgc.gesco.service.util;

import es.dgc.gesco.service.repository.EmailRepository;
import es.dgc.gesco.service.repository.NationalAuthorityRepository;
import es.dgc.gesco.service.repository.RoleRepository;
import es.dgc.gesco.service.repository.UserRepository;
import es.dgc.gesco.service.service.EmailService;
import es.dgc.gesco.service.service.NationalAuthorityService;
import es.dgc.gesco.service.service.RoleService;
import es.dgc.gesco.service.service.UserService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestFactory {

    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }

    @Bean
    public EmailService emailService(EmailRepository emailRepository){
        return new EmailService(emailRepository);
    }

    @Bean
    public RoleService roleService(RoleRepository roleRepository){
        return new RoleService(roleRepository);
        };

    @Bean
    public NationalAuthorityService nationalAuthorityService(NationalAuthorityRepository nationalAuthorityRepository){
        return new NationalAuthorityService(nationalAuthorityRepository);
    };
}
