package com.example.parkinglot.repositories;

import com.example.parkinglot.models.Operator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer> {

     Optional<Operator> findByEmail(String email);

     Operator save(Operator operator);

     Optional<Operator> findById(Integer id);
}
