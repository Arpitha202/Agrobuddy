package com.stackroute.cartservice.handler;

import com.stackroute.cartservice.exception.EmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerClass {
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler({EmailNotFoundException.class})
    private ResponseEntity<String> handleCityNotFound(EmailNotFoundException foundException){
        return new ResponseEntity("Email is not found", HttpStatus.NOT_FOUND);

    }
}
