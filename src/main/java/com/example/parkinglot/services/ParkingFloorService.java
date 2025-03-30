package com.example.parkinglot.services;

import com.example.parkinglot.exceptions.InvalidVehicleTypeException;
import com.example.parkinglot.models.ParkingFloor;
import com.example.parkinglot.models.ParkingFloorStatus;
import com.example.parkinglot.models.SupportedVehicle;
import com.example.parkinglot.repositories.ParkingFloorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingFloorService {
    private ParkingFloorRepository parkingFloorRepository;

    public ParkingFloorService(ParkingFloorRepository parkingFloorRepository) {
        this.parkingFloorRepository = parkingFloorRepository;
    }

    public ParkingFloor getAvailbleParkingFloor(SupportedVehicle supportedVehicle) throws InvalidVehicleTypeException {

        // get the floor which supports the given vehicle type
        Optional<ParkingFloor> optionalParkingFloor = parkingFloorRepository.findBySupportedVehicle(supportedVehicle);
        if (!optionalParkingFloor.isPresent()) {
            throw new InvalidVehicleTypeException("Given vehicle type is invalid.");
        }
        ParkingFloor parkingFloor = optionalParkingFloor.get();

        // check if floor is full
        if(parkingFloor.getStatus().equals(ParkingFloorStatus.FULL)){
            System.out.println("Parking floor is full.");
        }
        // check if floor is under maintenance
        else if (parkingFloor.getStatus().equals(ParkingFloorStatus.UNDER_MAINTENANCE)) {
            System.out.println("Parking floor is under maintenance.");
        }

        return parkingFloor;
    }

}
