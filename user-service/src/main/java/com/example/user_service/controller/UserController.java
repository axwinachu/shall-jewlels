package com.example.user_service.controller;

import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.SignupDto;
import com.example.user_service.facade.UserFacade;
import com.example.user_service.response.LoginResponse;
import com.example.user_service.response.SignupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserFacade userFacade;
    @PostMapping("/signup")
    public SignupResponse signup(@RequestBody SignupDto signupDto){
        return userFacade.signup(signupDto);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDto loginDto){
        return userFacade.login(loginDto);
    }
}
