package com.example.parkinglot.strategies;

import com.example.parkinglot.models.SupportedVehicle;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FourWheelerPaymentStrategy implements PaymentStrategy {

    @Override
    public int calculatePayment(Date entryTime, Date exitTime) {
        long durationInMillis = exitTime.getTime() - entryTime.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(durationInMillis);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(durationInMillis)%60;

        // charges per hour after 15 minutes
        if(minutes > 15){
            hours++;
        }

        int ratePerHour = 40;

        // charges increases after 8 hours of parking
        if(hours > 8){
            ratePerHour = 200;
        }

        return (int) hours * ratePerHour;

    }
}
