package es.consumo.junta_arbitral.jwt.rest.auth.logic.service.impl;

import es.consumo.junta_arbitral.jwt.rest.auth.clave.dto.ClaveAuthResponseData;
import es.consumo.junta_arbitral.jwt.rest.auth.clave.dto.ClaveRequestFormData;
import es.consumo.junta_arbitral.jwt.rest.auth.clave.service.ClaveService;
import es.consumo.junta_arbitral.jwt.rest.auth.logic.dto.AuthDataRecoveryReq;
import es.consumo.junta_arbitral.jwt.rest.auth.logic.dto.AuthModules;
import es.consumo.junta_arbitral.jwt.rest.auth.logic.dto.AuthUserDetails;
import es.consumo.junta_arbitral.jwt.rest.auth.logic.dto.UserSignInPetition;
import es.consumo.junta_arbitral.jwt.rest.auth.logic.service.TokenManagerService;
import es.consumo.junta_arbitral.jwt.rest.auth.logic.service.UserAuthService;
import es.consumo.junta_arbitral.jwt.rest.user.db.entity.LoginEntity;
import es.consumo.junta_arbitral.jwt.rest.user.db.entity.RoleHasModuleEntity;
import es.consumo.junta_arbitral.jwt.rest.user.db.entity.UserEntity;
import es.consumo.junta_arbitral.jwt.rest.user.db.repository.LoginRepository;
import es.consumo.junta_arbitral.jwt.rest.user.db.repository.RoleHasModuleRepository;
import es.consumo.junta_arbitral.jwt.rest.user.db.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@Service
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {
    private static final ConcurrentHashMap<String, LoginEntity> authorizationCodeStore = new ConcurrentHashMap<>();
    private final ClaveService claveService;
    private final RoleHasModuleRepository roleHasModuleRepository;
    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final TokenManagerService tokenManagerService;

    public UserAuthServiceImpl(ClaveService claveService, RoleHasModuleRepository roleHasModuleRepository,
                               LoginRepository loginRepository, UserRepository userRepository,
                               TokenManagerService tokenManagerService) {
        this.claveService = claveService;
        this.roleHasModuleRepository = roleHasModuleRepository;
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;
        this.tokenManagerService = tokenManagerService;
    }

    @Override
    @Transactional
    public ClaveRequestFormData requestAuthentication(UserSignInPetition petition) {
        log.info("Generating authentication identifier");

        ClaveRequestFormData claveAuth = this.claveService.requestAuthentication();


        log.info("Completed authentication request process");
        return claveAuth;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public LoginEntity createUserIfNotExits(ClaveAuthResponseData claveAuthResponseData) {
        Optional<LoginEntity> optional = loginRepository.findByUsername(claveAuthResponseData.getDocNum());
        LoginEntity login;
        if (optional.isEmpty()) {
            final LoginEntity loginEntity = new LoginEntity();
            loginEntity.setEnable(true);
            loginEntity.setUsername(claveAuthResponseData.getDocNum());
            loginEntity.setLastAccess(LocalDateTime.now());
            login = loginRepository.saveAndFlush(loginEntity);

            Optional<UserEntity> optionalUser = userRepository.findByDni(claveAuthResponseData.getDocNum());
            if (optionalUser.isEmpty()) {
                UserEntity user = new UserEntity();
                user.setDni(claveAuthResponseData.getDocNum());
                user.setId(login.getId());
                user.setName(claveAuthResponseData.getFirstName());
                user.setCreateAt(LocalDateTime.now());
                user.setSurname(claveAuthResponseData.getSurnames());
                login.setUser(userRepository.saveAndFlush(user));

            }
        } else {
            login = optional.get();
        }

        return login;
    }

    @Override
    public synchronized String generateAuthorizationCode(LoginEntity login) {
        String uuid = UUID.randomUUID().toString();
        if (!authorizationCodeStore.containsKey(uuid)) {
            authorizationCodeStore.put(uuid, login);
        } else {
            uuid = generateAuthorizationCode(login);
        }
        return uuid;
    }

    @Override
    @Transactional
    public OAuth2AccessToken signIn(AuthDataRecoveryReq req) {
        log.trace("Signing in User");
        String code = req.getAuthorizationCode();
        if (!authorizationCodeStore.containsKey(code)) {
            throw new RuntimeException("asd");
        }
        LoginEntity login = authorizationCodeStore.remove(code);
        OAuth2AccessToken token = tokenManagerService.getAccessToken(login);
        login.setLastAccess(LocalDateTime.now());
        loginRepository.save(login);
        log.trace("Sign in process completed");
        return token;
    }


    @Override
    @Transactional
    public ClaveRequestFormData signOut(LoginEntity loginEntity) {
        ClaveRequestFormData requestForm = this.claveService.requestLogout();
        try {
//			long now = JwtTime.now().timestamp();
//			AuthUserDetails jwtData = this.getAuthDetails(authorizationToken);
//			Optional<TulsaAuthUserToken> userAuth = this.userTokenRepository.findByPetitionIdAndExpAfter(jwtData.getPetitionId(), now);
//			userAuth.ifPresent(auth -> auth.setExp(now - 1));
        } catch (Exception e) {
            // Ignored
        }
        return requestForm;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthUserDetails getAuthDetails(LoginEntity loginEntity) {

        final Set<AuthModules> modules = getModules(loginEntity)
                .entrySet()
                .stream()
                .map(e -> new AuthModules(e.getKey(), e.getValue()))
                .collect(Collectors.toSet());
        final UserEntity user = loginEntity.getUser();
        return new AuthUserDetails(
                user.getId(),
                user.getDni(),
                user.getName(),
                user.getSurname(),
                user.getLastSurname(),
                null,
                modules
        );
    }

    private Map<String, Set<String>> getModules(LoginEntity loginEntity) {

        final Set<RoleHasModuleEntity.FullAuthoritiesProjection> modules =
                this.roleHasModuleRepository.findAllByLoginId(loginEntity.getId());

        return modules.stream()
                .collect(Collectors.groupingBy(RoleHasModuleEntity.FullAuthoritiesProjection::getModuleCode,
                        Collectors.mapping(RoleHasModuleEntity.FullAuthoritiesProjection::getPermissionCode, Collectors.toSet())));
    }


}
