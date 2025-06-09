package com.hms.user.user.Jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    // This class will contain methods for generating and validating JWT tokens.
    // For now, it's just a placeholder.

    public String generateToken(String username) {
        // Logic to generate JWT token
        return "generated-jwt-token-for-" + username;
    }

    public boolean validateToken(String token) {
        // Logic to validate JWT token
        return token != null && token.startsWith("generated-jwt-token-for-");
    }

    public String extractUsername(String token) {
        // Logic to extract username from JWT token
        return token.replace("generated-jwt-token-for-", "");
    }
}
