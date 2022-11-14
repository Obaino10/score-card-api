package com.obinna.scorecardapi.controller;

import com.obinna.scorecardapi.dto.requestdto.LoginDto;
import com.obinna.scorecardapi.dto.responsedto.LoginResponse;
import com.obinna.scorecardapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

        private  final AuthService authService;

        @PostMapping("/login")
        public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto) throws Exception {

            return new ResponseEntity<>(authService.login(loginDto), HttpStatus.OK);
        }


}
