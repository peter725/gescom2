package es.consumo.gescom.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.consumo.gescom.commons.exception.AppException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDto {

    /**
     * Fecha y Hora cuando ha ocurrido el error en formato UNIX timestamp
     */
    private Long timestamp;
    /**
     * Código público del error que ha ocurrido
     */
    private String code;
    /**
     * Descripción corta del error
     */
    private String error;
    /**
     * Mensaje del error
     */
    private String message;
    /**
     * Datos adicionales relevantes para el error.
     * No incluir datos sensibles.
     */
    private Object details;

    public ErrorDto(String code, String error, String message) {
        this(code, error, message, null, null);
    }

    public ErrorDto(String code, String error, String message, Object details, Long timestamp) {
        this.code = code;
        this.error = error;
        this.message = message;
        this.details = details;
        this.timestamp = timestamp != null ? timestamp : System.currentTimeMillis();
    }

    public static ErrorDto createValidationError(BindException ex) {
        String code = "ValidationError";
        String error = "Request data is not valid";
        ErrorDto err = new ErrorDto(code, error, null);
        List<HashMap<String, String>> details = new ArrayList<>();
        for (int i = 0; i < ex.getAllErrors().size(); i++) {
            ObjectError e = ex.getAllErrors().get(i);
            HashMap<String, String> detail = new HashMap<>();
            String key = (e instanceof FieldError) ? ((FieldError) e).getField() : ("Key_" + i);
            detail.put("field", key);
            detail.put("message", e.getDefaultMessage());
            details.add(detail);
        }
        err.setDetails(details);
        return err;
    }

    public static ErrorDto createDataAccessError(DataAccessException ex) {
        String code = "DATA_ACCESS_ERROR";
        String[] messages = ex.getMostSpecificCause().getMessage().trim().split(":");
        String error = messages[0].trim();
        String message = messages[1].trim();
        return new ErrorDto(code, error, message);
    }

    public static ErrorDto createGenericError(Exception ex) {
        String code = ex.getClass().getSimpleName();
        String error = "";
        if (ex.getCause() != null) {
            error = ex.getCause().getMessage();
        }
        return new ErrorDto(code, error, ex.getMessage());
    }

    public static ErrorDto createAppError(AppException ex) {
        ErrorDto dto = new ErrorDto(ex.getCode(), ex.getError(), ex.getMessage());
        dto.setTimestamp(ex.getTimestamp());
        return dto;
    }

    @Override
    public String toString() {
        String value = "Code: [%s] Message: [%s] Timestamp: [%d]";
        return String.format(value, code, message, timestamp);
    }
}
