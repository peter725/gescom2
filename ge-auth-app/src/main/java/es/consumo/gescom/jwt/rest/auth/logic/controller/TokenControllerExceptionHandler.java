package es.consumo.gescom.jwt.rest.auth.logic.controller;

import es.consumo.gescom.commons.controller.advice.GlobalControllerExceptionHandler;
import es.consumo.gescom.commons.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class TokenControllerExceptionHandler extends GlobalControllerExceptionHandler {


    @ExceptionHandler({InvalidTokenException.class})
    public ResponseEntity<ErrorDto> handleException(InvalidTokenException ex) {
        return processException(ErrorDto.createGenericError(ex), ex, HttpStatus.BAD_REQUEST);
    }


}
