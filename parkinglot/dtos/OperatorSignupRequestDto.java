package com.example.parkinglot.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OperatorSignupRequestDto {
    private String name;
    private String email;
    private String password;
}
