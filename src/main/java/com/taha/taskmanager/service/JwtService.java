package com.taha.taskmanager.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {

    private final SecretKey key;

public JwtService(@Value("${JWT_SECRET}") String secret) {
    this.key = Keys.hmacShaKeyFor(
            secret.getBytes(StandardCharsets.UTF_8)
    );
}

    public String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }
    public String extractEmail(String token) {
    return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
}
public boolean isTokenValid(String token) {
    try {
        Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);

        return true;
    } catch (Exception e) {
        return false;
    }
}
}
