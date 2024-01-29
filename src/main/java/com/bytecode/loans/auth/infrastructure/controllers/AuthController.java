package com.bytecode.loans.auth.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytecode.loans.auth.domain.entities.Auth;
import com.bytecode.loans.auth.domain.services.AuthService;
import com.bytecode.loans.auth.infrastructure.dtos.LoginDto;
import com.bytecode.loans.auth.infrastructure.dtos.RegisterDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto login) {
        return ResponseEntity.ok(authService.login(login));
    }

    @PostMapping("register")
    public ResponseEntity<Auth> register(@RequestBody RegisterDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

}
