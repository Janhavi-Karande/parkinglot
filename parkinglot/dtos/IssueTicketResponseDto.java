package com.example.parkinglot.dtos;

import com.example.parkinglot.models.Ticket;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IssueTicketResponseDto {
    private Ticket ticket;
    private ResponseStatus responseStatus;
}
