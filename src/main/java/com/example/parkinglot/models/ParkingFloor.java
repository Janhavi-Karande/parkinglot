package com.example.parkinglot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ParkingFloor extends BaseModel{
    private String floorNumber;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ParkingSpot> parkingSpots;

    @Enumerated(EnumType.ORDINAL)
    private ParkingFloorStatus status;

    @Enumerated(EnumType.ORDINAL)
    private SupportedVehicle supportedVehicle;

}
