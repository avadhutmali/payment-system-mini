package com.avadhut.payment.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_MS = 24*60*60*1000;

    public String generateToken(UUID userId, String role){

        return Jwts.builder()
                .setSubject(userId.toString())
                .addClaims(Map.of("role",role))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_MS))
                .signWith(key)
                .compact();
    }
}
