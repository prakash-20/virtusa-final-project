package com.examly.springapp.controller;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.model.*;
import com.examly.springapp.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping(path = "/user/booking")
    public List<GetAllBookingResponse> getAllBooking(){
        try {
            return bookingService.getAllBooking();
        } catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
        }
    }

    @PostMapping(path = "/user/booking/{vehicleId}")
    public CreateBookingResponse createBooking(@PathVariable int vehicleId, @RequestBody CreateBookingRequest createBookingRequest){
        try {
            return bookingService.createBooking(vehicleId, createBookingRequest);
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,exception.getMessage());
        }
    }

    @DeleteMapping(path = "/user/deleteBooking/{bookingId}")
    public int cancelBooking(@PathVariable int bookingId){
        try {
            return bookingService.cancelBooking(bookingId);
        } catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
        }
    }

    @PutMapping(path = "/user/editBooking/{bookingId}")
    public EditBookingResponse editBooking(@PathVariable int bookingId, @RequestBody EditBookingRequest editBookingRequest){
        try{
        return bookingService.editBooking(bookingId, editBookingRequest);
        } catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,exception.getMessage());
        }
    }

}
