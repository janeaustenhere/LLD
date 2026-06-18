package com.example.carRenterSystem.strategy;

import com.example.carRenterSystem.model.Vehicle;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("hourlyPricingStrategy")
public class HourlyPricingStrategy implements  PricingStartegy{
    @Override
    public double calculatePrice(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime, Integer distance) {

       long hours = Duration.between(startTime,endTime).toHours();

       return hours * vehicle.getPricePerHour();
    }
}
