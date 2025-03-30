package com.example.parkinglot.dtos;

import com.example.parkinglot.models.SupportedVehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueTicketRequestDto {
    private String vehicleNumber;
    private Integer gateId;
    private String ownerName;
    private Integer operatorId;
    private SupportedVehicle vehicleType;
    private String licenseNumber;
}
