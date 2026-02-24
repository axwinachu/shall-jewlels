package com.example.user_service.controller;

import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.SignupDto;
import com.example.user_service.facade.UserFacade;
import com.example.user_service.response.LoginResponse;
import com.example.user_service.response.SignupResponse;
import com.example.user_service.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserFacade userFacade;
    private final OpenAIService openAIService;
    @PostMapping("/signup")
    public SignupResponse signup(@RequestBody SignupDto signupDto){
        return userFacade.signup(signupDto);
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDto loginDto){
        return userFacade.login(loginDto);
    }

    @PostMapping("/help")
    public Flux<String> help(@RequestParam String question){
       return Flux.just(openAIService.askAI(question));
    }
}
