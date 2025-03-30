package com.example.parkinglot.repositories;

import com.example.parkinglot.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    Optional<Vehicle> findVehicleByVehicleNumber(String vehicleNumber);
    Vehicle save(Vehicle vehicle);

}
