package es.dgc.gesco.model.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundException extends AppException {
    private static final long serialVersionUID = 3L;

    public static final String DEFAULT_CODE = "T00_0001";
    public static final String DEFAULT_ERROR = "Elemento no encontrado";

    public NotFoundException() {
        super(DEFAULT_CODE, DEFAULT_ERROR, null, null);
    }

    public NotFoundException(String error) {
        super(DEFAULT_CODE, error, null, null);
    }

}
