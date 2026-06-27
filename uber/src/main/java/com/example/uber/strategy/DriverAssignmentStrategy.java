package com.example.uber.strategy;

import com.example.uber.model.Driver;
import com.example.uber.model.Ride;

import java.util.List;

public interface DriverAssignmentStrategy {

    Driver assignDriver(Ride ride, List<Driver> availableDriverList);
}
