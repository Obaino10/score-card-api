package com.obinna.scorecardapi.service;

import com.obinna.scorecardapi.dto.requestdto.LoginDto;
import com.obinna.scorecardapi.dto.responsedto.LoginResponse;

public interface AuthService{
    LoginResponse login(LoginDto loginDto) throws Exception;
}
