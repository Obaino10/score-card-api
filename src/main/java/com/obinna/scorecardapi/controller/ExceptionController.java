package com.obinna.scorecardapi.controller;

import com.obinna.scorecardapi.dto.responsedto.APIResponse;
import com.obinna.scorecardapi.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<APIResponse> userNotFoundException(UserNotFoundException exception){
        return new ResponseEntity<>(new APIResponse<>(true, exception.getMessage(), null), HttpStatus.NOT_FOUND);
    }



}




