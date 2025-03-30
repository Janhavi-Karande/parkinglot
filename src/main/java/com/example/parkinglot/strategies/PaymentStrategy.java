package com.example.parkinglot.strategies;

import com.example.parkinglot.models.SupportedVehicle;

import java.util.Date;

public interface PaymentStrategy {
    int calculatePayment(Date entryTime, Date exitTime);
}
