package com.example.parkinglot.repositories;

import com.example.parkinglot.models.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Integer> {
    ParkingSpot save(ParkingSpot parkingSpot);

    @Query(value = "SELECT ps.* FROM parking_spot ps " +
            "JOIN parking_floor_parking_spots pfps ON ps.id = pfps.parking_spots_id " +
            "WHERE pfps.parking_floor_id = :parkingFloorId",
            nativeQuery = true)

    List<ParkingSpot> findByParkingFloorId(@Param("parkingFloorId") Integer parkingFloorId);

}


