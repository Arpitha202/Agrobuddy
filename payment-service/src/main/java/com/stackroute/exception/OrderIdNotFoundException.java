package com.stackroute.exception;

public class OrderIdNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String message;

    public OrderIdNotFoundException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
