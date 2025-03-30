package com.example.parkinglot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel{
    private Long ticketNumber;
    private Date entryTime;
    @ManyToOne
    private ParkingFloor parkingFloor;

    @ManyToOne
    private ParkingSpot parkingSpot;

    @ManyToOne
    private Operator operator;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Gate gate;
}
