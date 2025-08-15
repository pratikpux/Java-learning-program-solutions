package com.example.authjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.example.authjwt.service.AuthenticationService;
import com.example.authjwt.model.TokenResponse;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestHeader("Authorization") String authHeader) {
        TokenResponse response = authenticationService.authenticate(authHeader);
        return ResponseEntity.ok(response);
    }
}