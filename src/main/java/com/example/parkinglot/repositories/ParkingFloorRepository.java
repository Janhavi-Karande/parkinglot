package com.example.parkinglot.repositories;

import com.example.parkinglot.models.ParkingFloor;
import com.example.parkinglot.models.SupportedVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkingFloorRepository extends JpaRepository<ParkingFloor, Integer> {
    Optional<ParkingFloor> findBySupportedVehicle(SupportedVehicle supportedVehicle);
    ParkingFloor save(ParkingFloor parkingFloor);
}
