package com.examly.springapp.model;

import lombok.Getter;

@Getter
public class PassengerResponse extends PassengerRequest{
    public PassengerResponse(String firstName, String lastName, String gender, int age) {
        super(firstName, lastName, gender, age);
    }
}
