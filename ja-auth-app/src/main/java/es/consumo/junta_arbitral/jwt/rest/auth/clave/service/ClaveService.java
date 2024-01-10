package es.consumo.junta_arbitral.jwt.rest.auth.clave.service;



import es.consumo.junta_arbitral.jwt.rest.auth.clave.dto.ClaveAuthResponseData;
import es.consumo.junta_arbitral.jwt.rest.auth.clave.dto.ClaveRequestFormData;

import javax.servlet.http.HttpServletRequest;

public interface ClaveService {

	/**
	 * Genera una nueva solicitud de autenticación por clave.
	 */
	ClaveRequestFormData requestAuthentication();

	ClaveRequestFormData requestLogout();

	/**
	 * Procesa la respuesta de clave en un formato amigable.
	 * @param request Request enviado por el servicio clave con la respuesta del usuario
	 */
	ClaveAuthResponseData processResponse(HttpServletRequest request);
}
