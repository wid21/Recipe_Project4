package com.example.springbootproject4.ApiException;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
