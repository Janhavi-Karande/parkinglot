package com.example.parkinglot.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenerateBillRequestDto {
    private Long tickerNumber;
    private Integer operatorId;
}
