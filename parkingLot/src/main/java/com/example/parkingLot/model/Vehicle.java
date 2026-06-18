package com.example.parkingLot.model;

import com.example.parkingLot.enums.VehicleType;
import lombok.Data;

import java.util.UUID;

@Data
public abstract class Vehicle {

    String vehicleId = UUID.randomUUID().toString();
    String licencePlate;
    VehicleType vehicleType;
    double pricePerHour;


    public Vehicle(String licencePlate, VehicleType vehicleType, double pricePerHour) {
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
        this.pricePerHour = pricePerHour;
    }
}
