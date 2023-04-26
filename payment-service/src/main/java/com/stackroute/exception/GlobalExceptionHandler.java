package com.stackroute.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    Environment environment;

    //@GlobalExceptionHandler(OrderIdNotFoundException.class)
    @ExceptionHandler(OrderIdNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleExceptionForIdNotFound(OrderIdNotFoundException exception) {
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
        errorInfo.setErrorCode("400");
        errorInfo.setTime(LocalDateTime.now());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
