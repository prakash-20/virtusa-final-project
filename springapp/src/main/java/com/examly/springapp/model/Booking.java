package com.examly.springapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Data
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
    @Column(name = "number_of_passanger")
    private int numberOfPassanger;

    @Basic
    @Column(name = "total_price")
    private double totalPrice;

    public Booking(Date fromDate, Date toDate, int numberOfPassanger, double totalPrice) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.numberOfPassanger = numberOfPassanger;
        this.totalPrice = totalPrice;
    }
}