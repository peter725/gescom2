package es.consumo.gescom.commons.controller.advice;

import es.consumo.gescom.commons.dto.ErrorDto;
import es.consumo.gescom.commons.exception.AppException;
import es.consumo.gescom.commons.exception.CustomValidationException;
import es.consumo.gescom.commons.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {
    @ExceptionHandler({NoSuchElementException.class, EntityNotFoundException.class, NotFoundException.class})
    public ResponseEntity<ErrorDto> handleException(NoSuchElementException ex) {
        return processException(ErrorDto.createGenericError(ex), ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({AppException.class})
    public ResponseEntity<ErrorDto> handleException(AppException ex) {
        return processException(ErrorDto.createAppError(ex), ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CustomValidationException.class})
    public ResponseEntity<ErrorDto> handleValidationException(CustomValidationException ex) {
        ErrorDto dto = new ErrorDto(ex.getCode(), ex.getError(), ex.getMessage());
        dto.setDetails(ex.getDetails());
        return processException(dto, ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<ErrorDto> handleException(BindException ex) {
        return processException(ErrorDto.createValidationError(ex), ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<ErrorDto> handleException(DataIntegrityViolationException ex) {
        return processException(ErrorDto.createDataAccessError(ex), ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return processException(ErrorDto.createGenericError(ex), ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<ErrorDto> processException(final ErrorDto dto, Exception ex, HttpStatus status) {
        log.error("Error {0}", ex);
        return ResponseEntity.status(status).body(dto);
    }

}
