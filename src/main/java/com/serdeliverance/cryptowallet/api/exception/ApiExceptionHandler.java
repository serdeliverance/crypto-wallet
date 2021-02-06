package com.serdeliverance.cryptowallet.api.exception;

import com.serdeliverance.cryptowallet.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<String> internalServerError(
            RuntimeException runtimeException
    ) {
        LOGGER.error("Unexpected error. Error {}", runtimeException.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<String> notFoundError(
            ResourceNotFoundException resourceNotFoundException
    ) {
        LOGGER.info("Resource not found. {}", resourceNotFoundException.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND).build();
    }

}
