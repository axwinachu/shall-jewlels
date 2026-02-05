package com.example.user_service.security;

import com.example.user_service.dto.LoginDto;
import com.example.user_service.enums.Role;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtUtil {
    private static final String SECRET = "my-super-secure-secret-key-for-jwt-signing-256-bit";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
    public String generateToken(Long userId,String email,String role){
        Map<String, Object> claims=new HashMap<>();
        claims.put("userId",userId);
        claims.put("role",role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
