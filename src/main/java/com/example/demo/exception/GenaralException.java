package com.example.demo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GenaralException {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException methodArgumentNotValidException) {

        final BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        final List<ObjectError> fieldErrors = bindingResult.getAllErrors();

        if (fieldErrors.size() > 1) {
            final List<ExceptionDetails> exceptionDetails = fieldErrors.stream()
                    .map(this::getErrorResponse)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
        }
        final ExceptionDetails exceptionDetails = fieldErrors.stream()
                .map(this::getErrorResponse)
                .findFirst().orElse(null);

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    private ExceptionDetails getErrorResponse(final ObjectError objectError) {

        final String message = getMessage(objectError.getCode());
        final String datetimeStamp = LocalDateTime.now().toString();
        final String code = HttpStatus.BAD_REQUEST.toString();
        return ExceptionDetails.of(datetimeStamp, code, message);
    }

    private String getMessage(final String message) {

        final Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(message, null, locale);
        } catch (NoSuchMessageException e) {
            return message;
        }
    }

/*    @ExceptionHandler
    public ResponseEntity<ExceptionDetails> handleException(Exception e) {
        final LocalDateTime today = LocalDateTime.now();
        final String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        if (e instanceof ResourceNotFoundException) {
            final ExceptionDetails exceptionDetails = ExceptionDetails.of(formattedDate, e.getMessage());
            return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
        } else {

        }
        final ExceptionDetails exceptionDetails = ExceptionDetails.of(formattedDate, e.getMessage());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

   /* @ExceptionHandler(ClassCastException.class)
    public ResponseEntity<ExceptionDetails> handleClassCastException(
            final ClassCastException e) {

        final LocalDateTime today = LocalDateTime.now();
        final String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        final ExceptionDetails exceptionDetails = ExceptionDetails.of(formattedDate, e.getMessage());
        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
