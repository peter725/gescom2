package es.dgc.gesco.service.util;

import es.dgc.gesco.service.repository.*;
import es.dgc.gesco.service.service.*;
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
    public AuthorityOEUService authorityOEUService(AuthorityOEURepository authorityOEURepository){
        return new AuthorityOEUService(authorityOEURepository);
    };

    @Bean
    public AutonomousCommunityService autonomousCommunityService(AutonomousCommunityRepository autonomousCommunityRepository){
        return new AutonomousCommunityService(autonomousCommunityRepository);
    };

    @Bean
    public AuthorityDGCService authorityDGCservice(AuthorityDGCRepository authorityDGCRepository){
        return new AuthorityDGCService(authorityDGCRepository);
    };


    @Bean
    public ProfileService profileService(ProfileRepository profileRepository){
        return new ProfileService(profileRepository);
    };

    @Bean
    public RoleService profileRoleService(RoleRepository RoleRepository){
        return new RoleService(RoleRepository);
    };

    @Bean
    public ApproachService approachService(ApproachRepository approachRepository){
        return new ApproachService(approachRepository);
    };
}
