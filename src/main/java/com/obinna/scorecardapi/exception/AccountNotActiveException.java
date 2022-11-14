package com.obinna.scorecardapi.exception;

public class AccountNotActiveException extends RuntimeException{
    public AccountNotActiveException(String message){
        super(message);
    }
}
