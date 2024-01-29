package com.bytecode.loans.auth.domain.services;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bytecode.loans.auth.domain.entities.Auth;
import com.bytecode.loans.auth.infrastructure.dtos.LoginDto;
import com.bytecode.loans.auth.infrastructure.dtos.RegisterDto;
import com.bytecode.loans.jwt.application.JwtService;
import com.bytecode.loans.users.domain.entities.User;
import com.bytecode.loans.users.domain.enums.Role;
import com.bytecode.loans.users.domain.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Auth login(LoginDto loginDto) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        UserDetails user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return Auth.builder().token(token).user(user).build();
    }

    public Auth register(RegisterDto registerDto) {
        User user = User.builder()
                .username(registerDto.getUsername())
                .name(registerDto.getName())
                .secondname(registerDto.getSecondname())
                .lastname(registerDto.getLastname())
                .secondlastname(registerDto.getSecondlastname())
                .phone(registerDto.getPhone())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .role(Role.USER)
                .build();

        User userInStore = this.userRepository.save(user);
        return Auth.builder().token(jwtService.getToken(userInStore)).user(userInStore).build();
    }
}
