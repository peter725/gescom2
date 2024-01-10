package es.consumo.junta_arbitral.commons.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomValidationException extends AppException {
    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_CODE = "T00_0001";
    public static final String DEFAULT_ERROR = "Los datos presentados son incorrectos";
    public static final String DEFAULT_MESSAGE = "Por favor revisa los datos presentados";

    private Object details;

    public CustomValidationException() {
        super(DEFAULT_CODE, DEFAULT_ERROR, DEFAULT_MESSAGE, null);
    }

    public CustomValidationException(Object details) {
        super(DEFAULT_CODE, DEFAULT_ERROR, null, null);
        this.details = details;
    }

    public CustomValidationException(String code, String error, Object details) {
        super(code, error, null, null);
        this.details = details;
    }

}
