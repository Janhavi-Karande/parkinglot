package com.example.parkinglot.services;

import com.example.parkinglot.exceptions.TicketNotFoundException;
import com.example.parkinglot.exceptions.UserNotFoundException;
import com.example.parkinglot.factories.PaymentStrategyFactory;
import com.example.parkinglot.models.*;
import com.example.parkinglot.repositories.BillRepository;
import com.example.parkinglot.repositories.ParkingFloorRepository;
import com.example.parkinglot.repositories.ParkingSpotRepository;
import com.example.parkinglot.strategies.PaymentStrategy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

@Service
public class BillService {

    private TicketService ticketService;
    private OperatorService operatorService;
    private BillRepository billRepository;
    private ParkingSpotRepository parkingSpotRepository;
    private ParkingFloorRepository parkingFloorRepository;
    private Scanner scanner = new Scanner(System.in);

    public BillService(TicketService ticketService, OperatorService operatorService,
                       BillRepository billRepository, ParkingSpotRepository parkingSpotRepository,
                       ParkingFloorRepository parkingFloorRepository) {

        this.ticketService = ticketService;
        this.operatorService = operatorService;
        this.billRepository = billRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingFloorRepository = parkingFloorRepository;

    }

    public Bill generateBill(Long ticketNumber, Integer operatorId) throws TicketNotFoundException, UserNotFoundException {

        // get the ticket object
        Ticket ticket = ticketService.getTicket(ticketNumber);

        // check if bill already exists
        Optional<Bill> optionalBill = billRepository.findByTicketId(ticket.getId());

        if(optionalBill.isPresent()) {
            System.out.println("Bill already exists");
            return optionalBill.get();
        }

        // create new bill object
        Bill bill = new Bill();

        Random randomNumber = new Random();
        bill.setBillNumber(Math.abs(randomNumber.nextLong()));

        bill.setStatus(BillStatus.UNPAID);
        bill.setExitTime(new Date());

        // set ticket for bill object
        bill.setTicket(ticket);

        // get entry time from ticket object
        Date entryTime = ticket.getEntryTime();

        // get vehicle type
        SupportedVehicle vehicleType = ticket.getVehicle().getVehicleType();

        // get payment strategy object based on vehicle type
        PaymentStrategy paymentStrategy = PaymentStrategyFactory.getPaymentStrategy(vehicleType);

        // get calculated amount for the given vehicle
        int amount = paymentStrategy.calculatePayment(entryTime, bill.getExitTime());

        // ask the operator if payment is done or not
        System.out.println("Amount to be paid: " + amount);
        //System.out.println("Did owner paid the amount(y/n):");
        //String isPaid = scanner.nextLine();

        // set bill status as paid once the payment id done
        //if (isPaid.equals("y")) {
            bill.setAmount(amount);
            bill.setStatus(BillStatus.PAID);
        //}

        // get and set operator object for bill
        Operator operator = operatorService.getOperator(operatorId);
        bill.setOperator(operator);

        // check if parking floor's status is full
        ParkingFloor parkingFloor = ticket.getParkingFloor();
        if(parkingFloor.getStatus().equals(ParkingFloorStatus.FULL)) {
            // if full change to available
            parkingFloor.setStatus(ParkingFloorStatus.AVAILABLE);
            parkingFloorRepository.save(parkingFloor);
        }

        // change the parking spot's status to available
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        parkingSpot.setParkingSpotStatus(ParkingSpotStatus.AVAILABLE);
        parkingSpotRepository.save(parkingSpot);

        // save in database and return
        return billRepository.save(bill);
    }
}
