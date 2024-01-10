package es.consumo.junta_arbitral.modules.role.service.impl;

import es.consumo.junta_arbitral.modules.role.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final LoginRepository loginRepository;

    public UserDetailServiceImpl(LoginRepository userRepository) {
        this.loginRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return loginRepository.findByUsername(username).orElseThrow();
        } catch (Exception ex) {
            String message = "user : '" + username + "' not found";
            log.error("[{}]", message);
            throw new UsernameNotFoundException(message);
        }
    }
}
