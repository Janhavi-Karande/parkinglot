package com.example.parkinglot.dtos;

import com.example.parkinglot.models.Bill;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class GenerateBillResponseDto {
    private Bill bill;
    private ResponseStatus responseStatus;
}
