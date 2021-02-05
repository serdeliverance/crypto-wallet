package com.serdeliverance.cryptowallet.api.exception;

import com.serdeliverance.cryptowallet.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<String> internalServerError(
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<String> notFoundError(
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND).build();
    }

}
