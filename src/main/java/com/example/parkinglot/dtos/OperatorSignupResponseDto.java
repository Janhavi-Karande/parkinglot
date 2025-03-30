package com.example.parkinglot.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OperatorSignupResponseDto {
    private int operatorId;
    private ResponseStatus responseStatus;
}
