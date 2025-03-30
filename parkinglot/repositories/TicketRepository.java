package com.example.parkinglot.repositories;

import com.example.parkinglot.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket save(Ticket ticket);
    Optional<Ticket> findByTicketNumber(Long ticketNumber);
}
