package com.examly.springapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
public class SignUpRequest {
    private final String email;
    private final String password;
    private final String name;
    private final String mobileNumber;
    private final String userRole;
}
