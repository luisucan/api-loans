package com.bytecode.loans.auth.domain.entities;

import org.springframework.security.core.userdetails.UserDetails;

import com.bytecode.loans.users.domain.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Auth {
    String token;
    UserDetails user;
}
