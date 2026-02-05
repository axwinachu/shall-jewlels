package com.example.user_service.dto;

import com.example.user_service.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class SignupDto {
        private Long id;
        private String username;
        private String email;
        private String password;
        private Role role;
        private boolean enabled = true;

}
