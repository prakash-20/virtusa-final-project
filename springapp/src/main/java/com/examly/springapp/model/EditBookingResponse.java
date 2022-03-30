package com.examly.springapp.model;

import com.examly.springapp.entity.Booking;
import lombok.Getter;

@Getter
public class EditBookingResponse extends CreateBookingResponse {
    public EditBookingResponse(Booking booking) {
        super(booking);
    }
}
