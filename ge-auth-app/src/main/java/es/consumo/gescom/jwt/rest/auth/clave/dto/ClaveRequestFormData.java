package es.consumo.gescom.jwt.rest.auth.clave.dto;

import lombok.*;

import java.util.Map;

/**
 * Estructura de datos para generar el formulario de comunicación con el servicio de clave.
 */
@Data
@AllArgsConstructor
public class ClaveRequestFormData {
	private String authUrl;
	private String relayId;
	private Map<String, String> authParams;
}
