package com.example.parkinglot.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Operator extends BaseModel{
    private String name;
    private String email;
    private String password;
}
