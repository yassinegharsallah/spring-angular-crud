package com.example.springrealworld.rest.controllers;

import com.example.springrealworld.rest.requests.AuthenticationRequest;
import com.example.springrealworld.rest.requests.RegisterRequest;
import com.example.springrealworld.rest.responses.AuthenticationResponse;
import com.example.springrealworld.domain.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @GetMapping("/current")
    public ResponseEntity<Object> getAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? (ResponseEntity<Object>) ResponseEntity.ok() : ResponseEntity.notFound().build();
    }
}
