package com.examly.springapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class LoginRequest {
    private final String email;
    private final String password;
}
