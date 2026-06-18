package com.example.parkingLot.strategy;

import com.example.parkingLot.model.Vehicle;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EventBasedPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(Vehicle vehicle, LocalDateTime entryTime, LocalDateTime exitTime) {
        return 0;
    }
}
