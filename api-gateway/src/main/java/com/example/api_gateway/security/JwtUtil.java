package com.example.api_gateway.security;

import com.example.api_gateway.exception.InvalidToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class JwtUtil {
    private final String SECRET_KEY="my-super-secure-secret-key-for-jwt-signing-256-bit";
    private Key getSiginKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    public Claims validateToken(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(getSiginKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception ex){
            throw new InvalidToken("token is not valid");
        }
    }
}
