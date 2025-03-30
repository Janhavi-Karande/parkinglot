package com.example.parkinglot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ParkingLot extends BaseModel{
    @OneToMany
    private List<ParkingFloor> parkingFloors;

    @OneToMany
    private List<Ticket> tickets;

    @OneToMany
    private List<Gate> gates;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<SupportedVehicle> supportedVehicles;
}
