package es.consumo.junta_arbitral.jwt.rest.auth.logic.service.impl;

import es.consumo.junta_arbitral.jwt.rest.user.db.entity.LoginEntity;
import es.consumo.junta_arbitral.jwt.rest.user.db.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final LoginRepository userRepository;

    public UserDetailServiceImpl(LoginRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userRepository.findByUsername(username).orElseThrow();
        } catch (Exception ex) {
            String message = "user : '" + username + "' not found";
            log.error("[{}]", message);
            throw new UsernameNotFoundException(message);
        }
    }
}
