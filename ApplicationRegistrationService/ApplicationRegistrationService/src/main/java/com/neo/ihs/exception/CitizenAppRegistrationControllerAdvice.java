package com.neo.ihs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CitizenAppRegistrationControllerAdvice {
    @ExceptionHandler(InvalidSSNException.class)
    public ResponseEntity<String> handleInvalidSSN(InvalidSSNException exception){
       return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public  ResponseEntity<String> handleAllException(Exception exception){
        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);

    }
}
