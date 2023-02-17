package com.example.testewipro.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandling {

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationExceptionCustom.class)
    public ErroMessage handlerValidationException(ValidationExceptionCustom ex) {
        return new ErroMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpClientErrorException.class)
    public ErroMessage handlerHttpClientException(HttpClientErrorException ex) {
        return new ErroMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErroMessage handlerResourceNotFoundException(ResourceNotFoundException ex) {
        return new ErroMessage(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErroMessage> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getAllErrors().stream().map(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            return new ErroMessage(errorMessage, fieldName);
        }).collect(Collectors.toList());
    }
}
