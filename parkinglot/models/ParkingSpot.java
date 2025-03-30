package com.example.parkinglot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ParkingSpot extends BaseModel{
    private String spotNumber;

    @Enumerated(EnumType.ORDINAL)
    private SupportedVehicle supportedVehicle;

    @Enumerated(EnumType.ORDINAL)
    private ParkingSpotStatus parkingSpotStatus;
}
