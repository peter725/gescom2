package es.consumo.junta_arbitral.jwt.rest.auth.logic.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedAccessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final String userFullName;

	public UnauthorizedAccessException() {
		super("User is not authorized to access this application");
		this.userFullName = "UNKNOWN";
	}

	public UnauthorizedAccessException(String userFullName) {
		super(String.format("User %s is not authorized to access this application", userFullName));
		this.userFullName = userFullName;
	}
}
