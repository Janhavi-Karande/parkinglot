package com.example.parkinglot.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Bill extends BaseModel{
    private Date exitTime;
    private Long billNumber;
    private int amount;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    private Operator operator;
    private BillStatus status;

}
