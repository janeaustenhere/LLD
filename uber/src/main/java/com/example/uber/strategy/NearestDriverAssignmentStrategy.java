package com.example.uber.strategy;

import com.example.uber.model.Driver;
import com.example.uber.model.Ride;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("nearestDriverAssignmentStrategy")
public class NearestDriverAssignmentStrategy implements DriverAssignmentStrategy{
    @Override
    public Driver assignDriver(Ride ride, List<Driver> availableDriverList) {
        return null;
    }
}
