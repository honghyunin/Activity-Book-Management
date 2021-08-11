package com.CRUD.test.advice.exception;

public class UserNotmatch extends RuntimeException{
    public UserNotmatch(String message){
        super(message);
    }
}
