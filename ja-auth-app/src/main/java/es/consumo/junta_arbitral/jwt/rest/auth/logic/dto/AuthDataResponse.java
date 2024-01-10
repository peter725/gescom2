package es.consumo.junta_arbitral.jwt.rest.auth.logic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Estructura de datos para enviar al usuario el token.
 *
 * @author serikat
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthDataResponse {
	private String token;
	private Long exp;
	// Otros posibles datos relevantes.
}
