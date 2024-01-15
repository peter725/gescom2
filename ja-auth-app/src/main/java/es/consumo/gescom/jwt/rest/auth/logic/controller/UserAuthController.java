package es.consumo.gescom.jwt.rest.auth.logic.controller;

import es.consumo.gescom.commons.constants.ApiEndpoints;
import es.consumo.gescom.jwt.rest.auth.clave.dto.ClaveRequestFormData;
import es.consumo.gescom.jwt.rest.auth.logic.dto.AuthDataRecoveryReq;
import es.consumo.gescom.jwt.rest.auth.logic.dto.AuthUserDetails;
import es.consumo.gescom.jwt.rest.auth.logic.dto.UserSignInPetition;
import es.consumo.gescom.jwt.rest.auth.logic.service.UserAuthService;
import es.consumo.gescom.jwt.rest.user.db.entity.LoginEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(ApiEndpoints.V1_API + "/auth/user")
@Slf4j
public class UserAuthController {

    private final UserAuthService userAuthService;

    @Autowired
    public UserAuthController(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @PostMapping("/request")
    public ResponseEntity<ClaveRequestFormData> requestAuthentication(@RequestBody @Valid UserSignInPetition petition) {
        log.info("Requesting authentication for User");
        return ResponseEntity.ok(this.userAuthService.requestAuthentication(petition));
    }

    @PostMapping("/sign_in")
    public ResponseEntity<OAuth2AccessToken> signIn(@RequestBody @Valid AuthDataRecoveryReq authDataReq) {
        log.info("Signing IN user");
        return ResponseEntity.ok(this.userAuthService.signIn(authDataReq));
    }

    @PostMapping("/sign_out")
    public ResponseEntity<ClaveRequestFormData> signOut(@AuthenticationPrincipal LoginEntity userDetails) {
        log.info("Signing OUT user");
        return ResponseEntity.ok(this.userAuthService.signOut(userDetails));
    }

    @GetMapping("/me")
    public ResponseEntity<AuthUserDetails> getAuthenticated(@AuthenticationPrincipal LoginEntity userDetails) {
        log.info("Getting authenticated user data");
        return ResponseEntity.ok(this.userAuthService.getAuthDetails(userDetails));
    }

}
