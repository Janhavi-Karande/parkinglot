package com.example.parkinglot.dtos;

import com.example.parkinglot.models.Operator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperatorLoginResponseDto {
    private Operator operator;
    private ResponseStatus status;
}
