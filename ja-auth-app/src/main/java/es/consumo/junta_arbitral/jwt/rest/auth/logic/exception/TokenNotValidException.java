package es.consumo.junta_arbitral.jwt.rest.auth.logic.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenNotValidException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TokenNotValidException() {
		super("Provided authorization token is not valid");
	}
}
