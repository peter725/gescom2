package es.consumo.gescom.jwt.rest.auth.logic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FakeSignInReq {
	private String docNum;
	private String secret;
}
