package com.crud.test.global.advice.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;

    public CustomException(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }
    public String getMessage() {return super.getMessage();}
    public HttpStatus getHttpStatus(){return httpStatus;}
}
