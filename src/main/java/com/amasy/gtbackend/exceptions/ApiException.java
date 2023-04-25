package com.amasy.gtbackend.exceptions;

public class ApiException extends RuntimeException {
    public ApiException(String message){
        super(message);
    }
}
