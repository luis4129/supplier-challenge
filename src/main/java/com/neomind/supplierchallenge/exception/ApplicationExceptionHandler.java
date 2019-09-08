package com.neomind.supplierchallenge.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ErrorResponse> handleTransactionSystemException(TransactionSystemException ex) {
        ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex.getCause().getCause();
        ConstraintViolation constraintViolation = constraintViolationException.getConstraintViolations().stream().findFirst().orElseThrow(UnexpectedException::new);

        return ResponseEntity.badRequest().body(new ErrorResponse(constraintViolation.getMessage()));
    }

    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private String message;

        public ErrorResponse(String message) {
            super();
            this.timestamp = LocalDateTime.now();
            this.message = message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getMessage() {
            return message;
        }

    }

}
