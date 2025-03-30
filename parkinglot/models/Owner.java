package com.example.parkinglot.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Owner extends BaseModel{
    private String licenseNumber;
    private String name;
}
