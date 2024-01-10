package es.consumo.junta_arbitral.jwt.rest.auth.logic.dto;

import es.consumo.junta_arbitral.jwt.rest.auth.clave.dto.ClaveRequestFormData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignInPetitionResp {
	private String petitionId;
	private String relayId;
	private String tempToken;
	private ClaveRequestFormData authData;
}
