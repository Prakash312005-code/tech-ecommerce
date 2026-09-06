package com.tech.gadget.tech.ecommerce.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // Secret key for signing JWT
    private final SecretKey key = Keys.hmacShaKeyFor(
            "mySecretKeyForTechGadgetApplication12345678901234567890"
                    .getBytes()
    );

    // Token expiration: 24 hours
    private final long jwtExpiration = 1000 * 60 * 60 * 24;


    // Generate JWT Token
    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis() + jwtExpiration
                        )
                )
                .signWith(key)
                .compact();
    }


    // Extract username from Token
    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }


    // Validate Token
    public boolean validateToken(String token) {

        try {

            extractUsername(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }
}