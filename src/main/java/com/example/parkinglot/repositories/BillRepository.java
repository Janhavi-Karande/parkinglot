package com.example.parkinglot.repositories;

import com.example.parkinglot.models.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    Bill save(Bill bill);
    Optional<Bill> findByTicketId(Integer ticketId);
}
