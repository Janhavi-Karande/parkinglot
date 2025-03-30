package com.example.parkinglot.services;

import com.example.parkinglot.exceptions.*;
import com.example.parkinglot.models.*;
import com.example.parkinglot.repositories.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class TicketService {
    private OperatorService operatorService;
    private VehicleService vehicleService;
    private OwnerService ownerService;
    private ParkingFloorService parkingFloorService;
    private TicketRepository ticketRepository;
    private GateRepository gateRepository;
    private ParkingSpotService parkingSpotService;

    public TicketService(GateRepository gateRepository, TicketRepository ticketRepository,
                         VehicleService vehicleService, OwnerService ownerService, OperatorService operatorService,
                         ParkingFloorService parkingFloorService, ParkingSpotService parkingSpotService) {

        this.gateRepository = gateRepository;
        this.ticketRepository = ticketRepository;
        this.vehicleService = vehicleService;
        this.ownerService = ownerService;
        this.operatorService = operatorService;
        this.parkingFloorService = parkingFloorService;
        this.parkingSpotService = parkingSpotService;
    }

    public Ticket issueTicket(String vehicleNumber, Integer gateId, String ownerName, Integer operatorId,
                              SupportedVehicle supportedVehicle, String licenseNumber)
            throws GateNotFoundException, InvalidVehicleNumberException, InvalidLicenseNumberException,
            UserNotFoundException, InvalidVehicleTypeException {

        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        // set ticket number
        Random randomNumber = new Random();
        ticket.setTicketNumber(Math.abs(randomNumber.nextLong()));

        // get the gate object from gate id
        Optional<Gate> optionalGate = gateRepository.findById(gateId);

        if (!optionalGate.isPresent()) {
            throw new GateNotFoundException("Gate with gate id " +gateId+ " does not exist.");
        }

        Gate gate = optionalGate.get();

        ticket.setGate(gate);

        // get the operator and set it for ticket object
        Operator operator = operatorService.getOperator(operatorId);
        ticket.setOperator(operator);

        // get owner and set it for ticket object
        Owner owner = ownerService.getOwner(ownerName, licenseNumber);
        ticket.setOwner(owner);

        // get the vehicle object and set it for ticket object
        Vehicle vehicle = vehicleService.getVehicle(vehicleNumber, owner, supportedVehicle);
        ticket.setVehicle(vehicle);

        // get the floor which supports the given vehicle type
        ParkingFloor parkingFloor = parkingFloorService.getAvailbleParkingFloor(supportedVehicle);
        ticket.setParkingFloor(parkingFloor);

        // get an empty spot in that floor
        ParkingSpot emptyParkingSpot = parkingSpotService.getAvailableParkingSpot(parkingFloor);
        ticket.setParkingSpot(emptyParkingSpot);

        // save and return the ticket object
        return ticketRepository.save(ticket);
    }

    public Ticket getTicket(Long ticketNumber) throws TicketNotFoundException {
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketNumber(ticketNumber);

        if (!optionalTicket.isPresent()) {
            throw new TicketNotFoundException("Ticket with ticket number " +ticketNumber + " does not exist.");
        }
        return optionalTicket.get();
    }
}
