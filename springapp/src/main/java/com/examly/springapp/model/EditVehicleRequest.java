package com.examly.springapp.model;

import java.sql.Time;

public class EditVehicleRequest extends AddVehicleRequest{
    public EditVehicleRequest(String name, String imageUrl, String address, String description, String availableStatus, Time time, int capacity, double ticketPrice) {
        super(name, imageUrl, address, description, availableStatus, time, capacity, ticketPrice);
    }
}
