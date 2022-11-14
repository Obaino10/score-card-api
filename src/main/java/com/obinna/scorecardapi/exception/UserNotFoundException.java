package com.obinna.scorecardapi.exception;

public class UserNotFoundException  extends  RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }

}
