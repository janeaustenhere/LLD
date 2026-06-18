package com.example.parkingLot.strategy;

import com.example.parkingLot.model.Vehicle;

import java.time.LocalDateTime;

public interface PricingStrategy {

    double calculatePrice(Vehicle vehicle,LocalDateTime entryTime,LocalDateTime exitTime);
}
