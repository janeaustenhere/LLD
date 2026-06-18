package com.example.parkingLot.strategy;

import com.example.parkingLot.model.Ticket;

public interface PaymentStrategy {

    double Pay(Ticket ticket);
}
