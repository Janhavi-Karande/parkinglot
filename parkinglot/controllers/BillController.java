package com.example.parkinglot.controllers;

import com.example.parkinglot.dtos.GenerateBillRequestDto;
import com.example.parkinglot.dtos.GenerateBillResponseDto;
import com.example.parkinglot.dtos.ResponseStatus;
import com.example.parkinglot.models.Bill;
import com.example.parkinglot.services.BillService;
import org.springframework.stereotype.Controller;

@Controller
public class BillController {
    private BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    public GenerateBillResponseDto generateBill(GenerateBillRequestDto generateBillRequestDto) {
        GenerateBillResponseDto generateBillResponseDto = new GenerateBillResponseDto();

        try{
            Bill bill = billService.generateBill(generateBillRequestDto.getTickerNumber(), generateBillRequestDto.getOperatorId());

            generateBillResponseDto.setBill(bill);
            generateBillResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e){
            generateBillResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            System.out.println("Error occurred while generating the bill"+e.getMessage());
        }

        return generateBillResponseDto;
    }
}
