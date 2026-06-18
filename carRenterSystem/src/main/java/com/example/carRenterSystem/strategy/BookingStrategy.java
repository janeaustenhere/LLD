package com.example.carRenterSystem.strategy;

import com.example.carRenterSystem.model.Vehicle;

import java.util.List;

public interface BookingStrategy {

    Vehicle bookVehicle(List<Vehicle> availableVehicleList);
}
