package com.example.ClassService.controller;

import com.example.ClassService.models.LoginResponse;
import com.example.ClassService.models.RegistrationPayload;
import com.example.ClassService.models.User;
import com.example.ClassService.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register (
            @RequestBody RegistrationPayload payload
            ) {
        return authService.register(payload.getUsername(), payload.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (
            @RequestBody RegistrationPayload payload
    ) {
        return authService.login(payload.getUsername(), payload.getPassword());
    }
}
