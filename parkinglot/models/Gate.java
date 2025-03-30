package com.example.parkinglot.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Gate extends BaseModel{
    @ManyToOne
    private Operator operator;

    @Enumerated(EnumType.ORDINAL)
    private GateType gateType;


}
