package com.crud.test.global.advice.exception;

public class UserNotmatch extends RuntimeException{
    public UserNotmatch(String message){
        super(message);
    }
}
