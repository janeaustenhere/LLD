package com.example.carRenterSystem.strategy;

import com.example.carRenterSystem.model.Vehicle;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("distanceBasePricingStrategy")
public class DistanceBasePricingStrategy implements PricingStartegy{
    @Override
    public double calculatePrice(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime, Integer distance) {
        return vehicle.getPricePerKm() * distance;
    }
}
