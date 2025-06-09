package com.hms.user.user.Jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private static final Long JWT_TOKEN_VALIDITY = 5 * 60 * 60L; // 10 hours in seconds
    private static final String SECRET_KEY="d8d8d23488aa9080de43d829b6b6a548cdcb288c53a023cd33ca4464cb89e9302b946f60f6b1132bc100e3a51f8bdf5b79be3c350b82dea52bb6a96279a2e8d9";

    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims= new HashMap<>();
        CustomUserDetails user= (CustomUserDetails) userDetails;
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().name());
        return doGenerateToken(claims, userDetails.getUsername());

    }

    public String doGenerateToken(Map<String,Object> claims,String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }


}
