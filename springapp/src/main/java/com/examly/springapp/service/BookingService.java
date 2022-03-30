package com.examly.springapp.service;

import com.examly.springapp.entity.Booking;
import com.examly.springapp.entity.Passenger;
import com.examly.springapp.entity.User;
import com.examly.springapp.entity.Vehicle;
import com.examly.springapp.model.*;
import com.examly.springapp.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final VehicleService vehicleService;
    private final ApplicationUserDetailsService applicationUserDetailsService;

    public List<GetAllBookingResponse> getAllBooking() {
        User currentLoggedInUser = applicationUserDetailsService.getCurrentLoggedInUser();
        return bookingRepository.findByUserId(currentLoggedInUser.getId()).stream().map(GetAllBookingResponse::new).collect(Collectors.toList());
    }

    @Transactional
    public CreateBookingResponse createBooking(int vehicleId, CreateBookingRequest createBookingRequest){
        Vehicle vehicle = vehicleService.findById(vehicleId).orElse(null);
        if (vehicle == null) throw new IllegalStateException("Vehicle is not present");
        if (!vehicle.getAvailableStatus().equalsIgnoreCase("Available")) throw new IllegalStateException(vehicle.getName()+" is not available to book");
        if (createBookingRequest.getNumberOfPassenger() != createBookingRequest.getPassengers().size()) throw new IllegalStateException("Number of passenger and list of passenger is not matched");
        if (createBookingRequest.getNumberOfPassenger() > vehicle.getCapacity()) throw new IllegalStateException("Passenger capacity exceeded");
        User currentLoggedInUser = applicationUserDetailsService.getCurrentLoggedInUser();
        Set<PassengerRequest> passengerRequest = createBookingRequest.getPassengers();
        Set<Passenger> passengers = passengerRequest.stream().map(Passenger::new).collect(Collectors.toSet());
        Booking newBooking = new Booking(createBookingRequest, currentLoggedInUser, vehicle, passengers);
        for (Passenger passenger : passengers){
            passenger.setBooking(newBooking);
        }
        bookingRepository.save(newBooking);
        modifyVehicleCapacity(newBooking, "createBooking");
        modifyVehicleAvailability(newBooking);

        return new CreateBookingResponse(newBooking);
    }


    @Transactional
    public int cancelBooking(int bookingId) {
        int currentUserId = applicationUserDetailsService.getCurrentLoggedInUser().getId();
        Booking existedBooking = bookingRepository.findByUserIdAndId(currentUserId,bookingId).orElse(null);
        if (existedBooking == null) throw new IllegalStateException("Couldn't find requested booking");
        modifyVehicleCapacity(existedBooking, "deleteBooking");
        modifyVehicleAvailability(existedBooking);
        bookingRepository.deleteById(bookingId);

        return bookingId;
    }

    @Transactional
    public EditBookingResponse editBooking(int bookingId, EditBookingRequest editBookingRequest) {
        int currentUserId = applicationUserDetailsService.getCurrentLoggedInUser().getId();
        Booking existedBooking = bookingRepository.findByUserIdAndId(currentUserId,bookingId).orElse(null);
        if (existedBooking == null) throw new IllegalStateException("Couldn't find requested booking");
        existedBooking.setFromDate(editBookingRequest.getFromDate());
        existedBooking.setToDate(editBookingRequest.getToDate());
        bookingRepository.save(existedBooking);

        return new EditBookingResponse(existedBooking);
    }

    public void modifyVehicleCapacity(Booking booking, String operation){
        Vehicle vehicle = booking.getVehicle();
        int vehicleCapacity = vehicle.getCapacity();
        int noOfPassengersInBooking = booking.getNumberOfPassenger();
        if (operation.equals("deleteBooking")){
            vehicle.setCapacity(vehicleCapacity + noOfPassengersInBooking);
        }
        else if(operation.equals("createBooking")){
            vehicle.setCapacity(vehicleCapacity - noOfPassengersInBooking);
        }
        EditVehicleRequest editVehicleRequest = new EditVehicleRequest(vehicle.getName(), vehicle.getImageUrl(), vehicle.getAddress(), vehicle.getDescription(), vehicle.getAvailableStatus(), vehicle.getTime(), vehicle.getCapacity(), vehicle.getTicketPrice());
        vehicleService.editVehicle(vehicle.getId(),editVehicleRequest);
    }

    public void modifyVehicleAvailability(Booking booking){
        Vehicle vehicle = booking.getVehicle();
        int vehicleCapacity = vehicle.getCapacity();
        if (vehicleCapacity == 0 ){
            vehicle.setAvailableStatus("Unavailable");
        }
        else if (vehicleCapacity > 0) {
            vehicle.setAvailableStatus("Available");
        }
        EditVehicleRequest editVehicleRequest = new EditVehicleRequest(vehicle.getName(), vehicle.getImageUrl(), vehicle.getAddress(), vehicle.getDescription(), vehicle.getAvailableStatus(), vehicle.getTime(), vehicle.getCapacity(), vehicle.getTicketPrice());
        vehicleService.editVehicle(vehicle.getId(),editVehicleRequest);
    }
}
