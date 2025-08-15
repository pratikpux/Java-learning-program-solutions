package com.example.authjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.authjwt.model.TokenResponse;
import com.example.authjwt.util.JwtUtil;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

@Service
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public TokenResponse authenticate(String authHeader) {
        // Extract credentials from Basic Auth header
        String[] credentials = extractCredentials(authHeader);
        String username = credentials[0];
        String password = credentials[1];

        // Validate credentials (for demo purposes, hardcoded user)
        if (isValidUser(username, password)) {
            String token = jwtUtil.generateToken(username);
            return new TokenResponse(token);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private String[] extractCredentials(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            throw new RuntimeException("Invalid Authorization header");
        }

        String base64Credentials = authHeader.substring(6);
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);

        String[] credentialsArray = credentials.split(":", 2);
        if (credentialsArray.length != 2) {
            throw new RuntimeException("Invalid credentials format");
        }

        return credentialsArray;
    }

    private boolean isValidUser(String username, String password) {
        // For demo purposes - hardcoded user validation
        // In production, this should validate against database
        return "user".equals(username) && "pwd".equals(password);
    }
}