package es.consumo.gescom.jwt.rest.auth.logic.service;

import es.consumo.gescom.jwt.rest.auth.clave.dto.ClaveAuthResponseData;
import es.consumo.gescom.jwt.rest.auth.clave.dto.ClaveRequestFormData;
import es.consumo.gescom.jwt.rest.auth.logic.dto.AuthDataRecoveryReq;
import es.consumo.gescom.jwt.rest.auth.logic.dto.AuthUserDetails;
import es.consumo.gescom.jwt.rest.auth.logic.dto.UserSignInPetition;
import es.consumo.gescom.jwt.rest.user.db.entity.LoginEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface UserAuthService {

    ClaveRequestFormData requestAuthentication(UserSignInPetition userSignInPetition);

    LoginEntity createUserIfNotExits(ClaveAuthResponseData claveAuthResponseData);

    String generateAuthorizationCode(LoginEntity login);

    OAuth2AccessToken signIn(AuthDataRecoveryReq authDataReq);

    ClaveRequestFormData signOut(LoginEntity loginEntity);

    AuthUserDetails getAuthDetails(LoginEntity loginEntity);

}
