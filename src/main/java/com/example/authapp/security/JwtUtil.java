package com.example.authapp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @org.springframework.beans.factory.annotation.Value("${app.jwt.secret}")
    private String secret;

    @org.springframework.beans.factory.annotation.Value("${app.jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // ✅ Generate Token
    public String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ Extract Email
    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ Extract Role
    public String extractRole(String token) {
        return getClaims(token).get("role", String.class);
    }

    // ✅ Validate Token
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired");
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported token");
        } catch (MalformedJwtException e) {
            System.out.println("Invalid token");
        } catch (Exception e) {
            System.out.println("Token error: " + e.getMessage());
        }
        return false;
    }

    // 🔒 Internal method
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}