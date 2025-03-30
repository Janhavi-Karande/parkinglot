package com.example.parkinglot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vehicle extends BaseModel {
    private String vehicleNumber;

    @ManyToOne
    private Owner owner;

    @Enumerated(EnumType.ORDINAL)
    private SupportedVehicle vehicleType;
}
