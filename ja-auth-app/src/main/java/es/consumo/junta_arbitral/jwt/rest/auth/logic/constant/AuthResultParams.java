package es.consumo.junta_arbitral.jwt.rest.auth.logic.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthResultParams {
	public static final String AUTH_ACTION_PARAM = "auth_action";
	public static final String AUTH_STATUS_PARAM = "auth_status";
	public static final String AUTH_CODE_PARAM = "auth_code";
	public static final String AUTH_MESSAGE_PARAM = "auth_message";
	public static final String RESULT_OK = "OK";
	public static final String RESULT_KO = "KO";
	public static final String ACTION_SIGN_IN = "sign_in";
	public static final String ACTION_SIGN_OUT = "sign_in";
}
