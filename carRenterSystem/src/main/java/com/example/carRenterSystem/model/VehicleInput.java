package com.example.carRenterSystem.model;


import com.example.carRenterSystem.enums.VehicleStatus;
import com.example.carRenterSystem.enums.VehicleType;
import lombok.Data;

import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class VehicleInput {

    String vehicleId;
    String licensePlate;
    String model;
    String vehicleType;
    double pricePerHour;
    double pricePerKm;
}
