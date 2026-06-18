package com.example.parkingLot.strategy;

import com.example.parkingLot.model.Vehicle;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("timeBasedPricingStrategy")
public class TimeBasedPricingStrategy implements PricingStrategy{
    @Override
    public double calculatePrice(Vehicle vehicle, LocalDateTime entryTime, LocalDateTime exitTime) {
       long hours = (long) Math.ceil(Duration.between(entryTime,exitTime).toHours());
       hours = hours == 0 ? 1 : hours;
       return hours * vehicle.getPricePerHour();

    }
}
