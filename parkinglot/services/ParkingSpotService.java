package com.example.parkinglot.services;

import com.example.parkinglot.models.ParkingFloor;
import com.example.parkinglot.models.ParkingFloorStatus;
import com.example.parkinglot.models.ParkingSpot;
import com.example.parkinglot.models.ParkingSpotStatus;
import com.example.parkinglot.repositories.ParkingFloorRepository;
import com.example.parkinglot.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotService {
    private ParkingSpotRepository parkingSpotRepository;
    private ParkingFloorRepository parkingFloorRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository, ParkingFloorRepository parkingFloorRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.parkingFloorRepository = parkingFloorRepository;
    }

    // get available parking spot
    public ParkingSpot getAvailableParkingSpot(ParkingFloor parkingFloor) {

        // get an empty spot in the given floor
        ParkingSpot emptyParkingSpot = null;
        for(ParkingSpot parkingSpot : parkingFloor.getParkingSpots()){
            if(parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.AVAILABLE)){
                emptyParkingSpot = parkingSpot;
                break;
            }
        }

        // set this parking spot's status as full
        if(emptyParkingSpot != null) {
            emptyParkingSpot.setParkingSpotStatus(ParkingSpotStatus.FULL);
            parkingSpotRepository.save(emptyParkingSpot);
        }

        // check whether parking spot is last available spot or not, if yes change the parking floor's status as full
        // get all spots with parking floor id
        List<ParkingSpot> parkingSpots = parkingSpotRepository.findByParkingFloorId(parkingFloor.getId());

        // count number of filled spots
        int parkingSpotCount = (int)(parkingSpots.stream().
                filter(spot -> spot.getParkingSpotStatus().equals(ParkingSpotStatus.FULL)).
                count());

        // check if all spots are full
        if(parkingSpots.size() == parkingSpotCount){
            // if yes change status of parking floor
            parkingFloor.setStatus(ParkingFloorStatus.FULL);
            parkingFloorRepository.save(parkingFloor);
        }

        return emptyParkingSpot;
    }

}
