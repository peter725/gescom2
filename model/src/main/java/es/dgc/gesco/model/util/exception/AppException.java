package es.dgc.gesco.model.util.exception;

import lombok.Getter;
import lombok.Setter;

import java.security.NoSuchAlgorithmException;

/**
 * Excepción personalizada para la aplicación.
 * <br/>
 * Toda excepción debería llevar un código único para su identificación.
 * <br/><br/>
 * Formato estándar del código: TMM_DDDD
 * <br/>
 * T - Tulsa
 * MM - Identificador numérico del módulo (00 a 99)
 * DDDD - Número secuencial del error
 */
@Getter
@Setter
public class AppException extends RuntimeException {
    private static final long serialVersionUID = 3L;
    public static final String DEFAULT_CODE = "T00_0000";
    public static final String DEFAULT_ERROR = "Error desconocido de la aplicación";

	/**
	 * Código identificativo del error. Por defecto {@link DEFAULT_CODE}
	 */
    private final String code;
    private final String error;
    private final Long timestamp;

    public AppException() {
        this(DEFAULT_CODE, DEFAULT_ERROR, null, null);
    }

    public AppException(String code, String error, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.error = error;
        this.timestamp = System.currentTimeMillis();
    }

    public AppException(String code, NoSuchAlgorithmException e) {
        super();
        this.code = code;
        this.error = code;
        this.timestamp = System.currentTimeMillis();
    }

    public AppException(String encryptionErrorLiteral, Exception e) {
        super();
        this.code = encryptionErrorLiteral;
        this.error = code;
        this.timestamp = System.currentTimeMillis();
    }
}
