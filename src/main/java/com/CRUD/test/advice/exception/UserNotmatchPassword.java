package com.CRUD.test.advice.exception;

public class UserNotmatchPassword extends RuntimeException{
    public UserNotmatchPassword(String message){
        super(message);
    }
}
