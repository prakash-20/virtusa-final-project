package com.examly.springapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.util.Set;

@Getter
@AllArgsConstructor
public class CreateBookingRequest {
    private final Date fromDate;
    private final Date toDate;
    private final int numberOfPassenger;
    private final double totalPrice;
    private final Set<PassengerRequest> passengers;
}
