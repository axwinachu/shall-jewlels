package com.example.user_service.facade;

import com.example.user_service.dto.LoginDto;
import com.example.user_service.dto.SignupDto;
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

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService userService;
    private final AuthMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public SignupResponse signup(SignupDto signupDto) {
        if(userService.existByEmail(signupDto.getEmail())){
            throw new UserAlreadyExist("Email already exist");
        }
        User user=mapper.signUpDtoToUser(signupDto);
        userService.save(user);
        return new SignupResponse("SIGN_UP_SUCCESSFULLY",signupDto.getUsername()+"signup successfully");
    }

    public LoginResponse login(LoginDto loginDto) {
        if(!userService.existByEmail(loginDto.getEmail())){
            throw new UserNotFound("user not found in the mail"+loginDto.getEmail());
        }
        User user=userService.findByEmail(loginDto.getEmail());
        if(!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())){
            throw new IncorrectPassword("invalid password please check");
        }
        return new LoginResponse("LoginSuccess", jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole().name()));
    }
}

