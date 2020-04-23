package com.cristal.stefanie.cursomc.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(expiration).toInstant(ZoneOffset.UTC)))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
}
