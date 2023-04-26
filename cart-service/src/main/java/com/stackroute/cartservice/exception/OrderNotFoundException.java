package com.stackroute.cartservice.exception;

public class OrderNotFoundException extends RuntimeException{
    private  String message;

    public OrderNotFoundException(String message)
    {
        super();
        this.message=message;
    }
    public String getMessage() {

        return message;
    }

}
