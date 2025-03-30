package com.example.parkinglot.controllers;

import com.example.parkinglot.dtos.*;
import com.example.parkinglot.models.Operator;
import com.example.parkinglot.services.OperatorService;
import org.springframework.stereotype.Controller;

@Controller
public class OperatorController {

    OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    public OperatorLoginResponseDto login( OperatorLoginRequestDto loginRequestDto)  {
        OperatorLoginResponseDto responseDto = new OperatorLoginResponseDto();
        try {
            Operator operator = operatorService.login(loginRequestDto.getEmail(),
                    loginRequestDto.getPassword());

            responseDto.setStatus(ResponseStatus.SUCCESS);

        }
        catch (Exception e) {
            responseDto.setStatus(ResponseStatus.FAILURE);
            System.out.println("Error occurred while login "+e.getMessage());
        }
        return responseDto;
    }

    public OperatorSignupResponseDto signup(OperatorSignupRequestDto operatorSignupRequestDto)  {
        OperatorSignupResponseDto responseDto = new OperatorSignupResponseDto();

        try {
            Operator operator = operatorService.sigup(operatorSignupRequestDto.getName(),
                    operatorSignupRequestDto.getEmail(), operatorSignupRequestDto.getPassword());

            responseDto.setOperatorId(operator.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);

        }
        catch (Exception e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
            System.out.println("Error occurred while signing in "+e.getMessage());
        }
        return responseDto;
    }
}
