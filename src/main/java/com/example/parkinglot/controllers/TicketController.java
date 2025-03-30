package com.example.parkinglot.controllers;

import com.example.parkinglot.dtos.IssueTicketRequestDto;
import com.example.parkinglot.dtos.IssueTicketResponseDto;
import com.example.parkinglot.dtos.ResponseStatus;
import com.example.parkinglot.models.Ticket;
import com.example.parkinglot.services.TicketService;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDto issueTicket(IssueTicketRequestDto issueTicketRequestDto) {

        IssueTicketResponseDto issueTicketResponseDto = new IssueTicketResponseDto();
        try{
            Ticket ticket = ticketService.issueTicket(issueTicketRequestDto.getVehicleNumber(), issueTicketRequestDto.getGateId(),
                    issueTicketRequestDto.getOwnerName(), issueTicketRequestDto.getOperatorId(), issueTicketRequestDto.getVehicleType(),
                    issueTicketRequestDto.getLicenseNumber());

            issueTicketResponseDto.setTicket(ticket);
            issueTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e){
            issueTicketResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            System.out.println("Error occurred while generating the ticket "+e.getMessage());
        }

        return issueTicketResponseDto;
    }
}
