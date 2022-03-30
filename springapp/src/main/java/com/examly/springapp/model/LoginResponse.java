package com.examly.springapp.model;

import com.examly.springapp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private final int id;
    private final String email;
    private final String name;
    private final String mobileNumber;
    private final String userRole;

    public LoginResponse(User userObject) {
        this.id = userObject.getId();
        this.email = userObject.getEmail();
        this.name = userObject.getName();
        this.mobileNumber = userObject.getMobileNumber();
        this.userRole = userObject.getUserRole();
    }
}
