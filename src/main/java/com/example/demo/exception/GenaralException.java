package com.example.demo.exception;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class GenaralException {
    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handleException(Exception e) {

        if (e instanceof ResourceNotFoundException) {
            final LocalDateTime today = LocalDateTime.now();
            final String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            final ExceptionDetails exceptionDetails = ExceptionDetails.of(formattedDate, e.getMessage());
            return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
        } else {

        }
        return new ResponseEntity<>(null);
    }

    @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<ExceptionDetails> handleClassCastException(
            final ClassCastException e) {

        final LocalDateTime today = LocalDateTime.now();
        final String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        final ExceptionDetails exceptionDetails = ExceptionDetails.of(formattedDate, e.getMessage());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
