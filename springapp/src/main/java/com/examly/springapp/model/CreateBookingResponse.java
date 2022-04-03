package com.examly.springapp.model;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.entity.Vehicle;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class CreateBookingResponse{
    private final int id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private final Date fromDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private final Date toDate;
    private final Vehicle vehicle;
    private final int numberOfPassenger;
    private final double totalPrice;
    private final Set<PassengerResponse> passengers;

    public CreateBookingResponse(Booking booking) {
        this.id = booking.getId();
        this.fromDate = booking.getFromDate();
        this.toDate = booking.getToDate();
        this.numberOfPassenger = booking.getNumberOfPassenger();
        this.totalPrice = booking.getTotalPrice();
        this.vehicle = booking.getVehicle();
        this.passengers = booking.getPassengers().stream().map(p -> new PassengerResponse(
                p.getFirstName(),
                p.getLastName(),
                p.getGender(),
                p.getAge()
        )).collect(Collectors.toSet());
    }
}
