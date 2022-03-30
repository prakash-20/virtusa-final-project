package com.examly.springapp.entity;

import com.examly.springapp.model.CreateBookingRequest;
import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Basic
    @Column(name = "from_date")
    private Date fromDate;

    @Basic
    @Column(name = "to_date")
    private Date toDate;

    @Basic
    @Column(name = "number_of_passenger")
    private int numberOfPassenger;

    @Basic
    @Column(name = "total_price")
    private double totalPrice;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    private Vehicle vehicle;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy="booking", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Passenger> passengers = new HashSet<Passenger>();


    private Booking(Date fromDate, Date toDate, int numberOfPassenger, double totalPrice) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.numberOfPassenger = numberOfPassenger;
        this.totalPrice = totalPrice;
    }

    public Booking(CreateBookingRequest createBookingRequest, User user, Vehicle vehicle, Set<Passenger> passengers) {
        this(createBookingRequest.getFromDate(),createBookingRequest.getToDate(),createBookingRequest.getNumberOfPassenger(),createBookingRequest.getTotalPrice());
        this.setUser(user);
        this.setVehicle(vehicle);
        this.setPassengers(passengers);
    }
}
