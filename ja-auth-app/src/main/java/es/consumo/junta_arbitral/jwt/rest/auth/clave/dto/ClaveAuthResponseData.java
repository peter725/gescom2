package es.consumo.junta_arbitral.jwt.rest.auth.clave.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClaveAuthResponseData {
	private String docNum;
	private String relayId;
	private String firstName;
	private String surnames;

	public String getFullName() {
		return firstName + " " + surnames;
	}
}
