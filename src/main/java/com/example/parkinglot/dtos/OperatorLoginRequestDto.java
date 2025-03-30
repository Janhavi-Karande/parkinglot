package com.example.parkinglot.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperatorLoginRequestDto {
    private String email;
    private String password;
}
