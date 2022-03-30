package com.examly.springapp.model;

import com.examly.springapp.entity.Booking;
import lombok.Getter;

@Getter
public class GetAllBookingResponse extends CreateBookingResponse{
    public GetAllBookingResponse(Booking booking) {
        super(booking);
    }
}
