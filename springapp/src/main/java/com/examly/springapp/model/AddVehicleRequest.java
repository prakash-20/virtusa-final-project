package com.examly.springapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Time;

@Getter
@AllArgsConstructor
public class AddVehicleRequest {
    private final String name;
    private final String imageUrl;
    private final String address;
    private final String description;
    private final String availableStatus;
    @JsonFormat(pattern="HH:mm:ss")
    private final Time time;
    private final int capacity;
    private final double ticketPrice;
}
