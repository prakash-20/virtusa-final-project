package com.examly.springapp.model;

import lombok.Getter;

import java.sql.Date;
import java.util.Set;

@Getter
public class EditBookingRequest extends CreateBookingRequest{
    public EditBookingRequest(Date fromDate, Date toDate, int numberOfPassenger, double totalPrice, Set<PassengerRequest> passengers) {
        super(fromDate, toDate, numberOfPassenger, totalPrice, passengers);
    }
}
