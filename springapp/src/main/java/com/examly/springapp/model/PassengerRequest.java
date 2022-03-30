package com.examly.springapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PassengerRequest {
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final int age;
}
