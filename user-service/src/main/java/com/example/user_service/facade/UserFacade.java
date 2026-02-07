package com.example.user_service.facade;

import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.SignupDto;
import com.example.user_service.enums.ExceptionMessages;
import com.example.user_service.exception.IncorrectPassword;
import com.example.user_service.exception.UserAlreadyExist;
import com.example.user_service.exception.UserNotFound;
import com.example.user_service.mapper.AuthMapper;
import com.example.user_service.model.User;
import com.example.user_service.response.LoginResponse;
import com.example.user_service.response.SignupResponse;
import com.example.user_service.security.JwtUtil;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;
    private final AuthMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final KafkaTemplate<String,String> kafkaTemplate;
    public SignupResponse signup(SignupDto signupDto) {
        if(userService.existByEmail(signupDto.getEmail())){
            throw new UserAlreadyExist(ExceptionMessages.EMAIL_ALREADY_EXIST.name());
        }
        User user=mapper.signUpDtoToUser(signupDto);
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        userService.save(user);
        return new SignupResponse(ExceptionMessages.SIGNUP_SUCCESS.name(),signupDto.getUsername()+ExceptionMessages.SIGNUP_SUCCESS);
    }

    public LoginResponse login(LoginDto loginDto) {
        if(!userService.existByEmail(loginDto.getEmail())){
            throw new UserNotFound(ExceptionMessages.USER_NOT_FOUND.name()+loginDto.getEmail());
        }
        User user=userService.findByEmail(loginDto.getEmail());
        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            throw new IncorrectPassword(ExceptionMessages.INVALID_PASSWORD_PLEASE_CHECK.name());
        }
        return new LoginResponse(ExceptionMessages.LOGIN_SUCCESS.name(), jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().name()));
    }

}

