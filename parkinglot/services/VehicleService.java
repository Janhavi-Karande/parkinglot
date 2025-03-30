package com.example.parkinglot.services;

import com.example.parkinglot.exceptions.InvalidVehicleNumberException;
import com.example.parkinglot.models.Owner;
import com.example.parkinglot.models.SupportedVehicle;
import com.example.parkinglot.models.Vehicle;
import com.example.parkinglot.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    private final String numberPlateRegex = "^[A-Z]{2}\\s?[0-9]{2}\\s?[A-Z]{0,2}\\s?[0-9]{4}$";

    //method to check valid vehicle number
    public boolean checkVehicleNumber(String vehicleNumber) {

        return Pattern.matches(numberPlateRegex, vehicleNumber);
    }

    // method to get vehicle object
    public Vehicle getVehicle(String vehicleNumber, Owner owner,
                              SupportedVehicle supportedVehicle) throws InvalidVehicleNumberException {

        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicleByVehicleNumber(vehicleNumber);
        Vehicle vehicle;
        if (optionalVehicle.isPresent()) {
            vehicle = optionalVehicle.get();
        }
        else {
            vehicle = new Vehicle();
            // check vehicle number
            if(checkVehicleNumber(vehicleNumber)) {
                vehicle.setVehicleNumber(vehicleNumber);
            }
            else
                throw new InvalidVehicleNumberException("Vehicle number " + vehicleNumber + " is not valid.");
            vehicle.setOwner(owner);
            vehicle.setVehicleType(supportedVehicle);
            vehicleRepository.save(vehicle);
        }
        return vehicle;
    }
}
