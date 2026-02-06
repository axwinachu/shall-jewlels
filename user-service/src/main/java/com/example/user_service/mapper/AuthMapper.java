package com.example.user_service.mapper;

import com.example.user_service.dto.SignupDto;
import com.example.user_service.enums.Role;
import com.example.user_service.model.User;
import com.example.user_service.security.SecurityFilter;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Sides;

@Component
@RequiredArgsConstructor
public class AuthMapper {
    public User signUpDtoToUser(SignupDto dto){
       return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .role(Role.USER)
                .password(dto.getPassword()).build();
    }
}
