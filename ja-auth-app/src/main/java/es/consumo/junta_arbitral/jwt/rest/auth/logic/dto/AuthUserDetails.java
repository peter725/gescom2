package es.consumo.junta_arbitral.jwt.rest.auth.logic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import es.consumo.junta_arbitral.jwt.rest.user.db.entity.ArbitrationBoardEntity;

/**
 * Detalles del usuario
 *
 * @author serikat
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserDetails {
	private Long userId;
	private String docNum;

	private String firstName;
	private String firstSurname;
	private String secondSurname;
	private ArbitrationBoardEntity arbitrationBoard;

	private Set<AuthModules> modules;
}
