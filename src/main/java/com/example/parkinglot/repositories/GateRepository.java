package com.example.parkinglot.repositories;

import com.example.parkinglot.models.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GateRepository extends JpaRepository<Gate, Integer> {
    Optional<Gate> findGateById(int id);
}
