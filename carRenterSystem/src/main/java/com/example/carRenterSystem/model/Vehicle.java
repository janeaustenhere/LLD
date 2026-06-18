package com.example.carRenterSystem.model;


import com.example.carRenterSystem.enums.VehicleStatus;
import com.example.carRenterSystem.enums.VehicleType;
import lombok.Data;

import java.util.concurrent.atomic.AtomicBoolean;

@Data
public abstract class Vehicle {

    String vehicleId;
    String licensePlate;
    String model;
    VehicleType vehicleType;
    VehicleStatus vehicleStatus = VehicleStatus.AVAILABLE;
    int bookingCount = 0;
    double pricePerHour;
    double pricePerKm;
    AtomicBoolean isAvailable = new AtomicBoolean(true);

    public Vehicle(String vehicleId, String licensePlate, String model, VehicleType vehicleType,
                   double pricePerHour, double pricePerKm){

        this.vehicleId = vehicleId;
        this.licensePlate = licensePlate;
        this.model = model;
        this.vehicleType = vehicleType;
        this.pricePerHour = pricePerHour;
        this.pricePerKm = pricePerKm;
    }

    public void incrementBookingCount(){
        bookingCount++;
    }


}
