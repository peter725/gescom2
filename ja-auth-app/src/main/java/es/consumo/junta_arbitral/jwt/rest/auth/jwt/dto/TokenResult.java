package es.consumo.junta_arbitral.jwt.rest.auth.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResult {
	private String token;
	private String prefix;
	private Long exp;
}
