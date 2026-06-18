package com.example.carRenterSystem.strategy;

import com.example.carRenterSystem.model.Vehicle;

import java.time.LocalDateTime;

public interface PricingStartegy {

    double calculatePrice(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime, Integer distance);
}
