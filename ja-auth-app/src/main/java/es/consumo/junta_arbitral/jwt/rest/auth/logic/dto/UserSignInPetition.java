package es.consumo.junta_arbitral.jwt.rest.auth.logic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInPetition {
	private String docNum; // Opcional
	@NotNull
	private String processReturnUrl;
}
