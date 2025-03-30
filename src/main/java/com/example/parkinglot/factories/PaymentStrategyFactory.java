package com.example.parkinglot.factories;

import com.example.parkinglot.models.SupportedVehicle;
import com.example.parkinglot.strategies.FourWheelerPaymentStrategy;
import com.example.parkinglot.strategies.PaymentStrategy;
import com.example.parkinglot.strategies.TwoWheelerPaymentStrategy;

public class PaymentStrategyFactory {
    static public PaymentStrategy getPaymentStrategy(SupportedVehicle vehicleType) {
        if(vehicleType.equals(SupportedVehicle.TWO_WHEELER)){
            return new TwoWheelerPaymentStrategy();
        }
        else{
            return new FourWheelerPaymentStrategy();
        }

    }
}
