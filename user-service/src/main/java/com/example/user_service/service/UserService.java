package com.example.user_service.service;

import com.example.user_service.exception.UserNotFound;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean existByEmail(String email) {
        return  userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()->new UserNotFound("user Not found"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
