package com.bytecode.loans.auth.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    String username;
    String phone;
    String email;
    String name;
    String secondname;
    String lastname;
    String secondlastname;
    String password;
}
