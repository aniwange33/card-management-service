package com.vela.cardmanageservice.infrastructure.web.config;

import com.vela.cardmanageservice.infrastructure.web.model.ErrorResponse;
import com.vela.cardmanageservice.usecase.exception.GenericInputErrorException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionControllersAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GenericInputErrorException.class)
    public ResponseEntity<ErrorResponse> handleGenericTextExceptions(GenericInputErrorException e, WebRequest request) {
        return ResponseEntity.badRequest().body(ErrorResponse.createErrorResponse(LocalDateTime.now(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleExceptions(GenericInputErrorException e, WebRequest request) {
        return ResponseEntity.badRequest().body(ErrorResponse.createErrorResponse(LocalDateTime.now(), request.getDescription(true)));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(ErrorResponse.createErrorResponse(LocalDateTime.now(), ex.getBindingResult().toString()));
    }

}
