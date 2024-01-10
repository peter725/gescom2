package es.consumo.junta_arbitral.jwt.rest.auth.logic.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Solicitud de recuperación de los datos de autenticación del usuario.
 *
 * @author serikat
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AuthDataRecoveryReq {

	@NotNull
	@NotBlank
	private String authorizationCode;
}
