package com.example.parkingLot.model;

import com.example.parkingLot.enums.VehicleType;

public class Bike extends Vehicle{

    public Bike(VehicleInput vehicleInput){

        super(vehicleInput.getLicencePlate(), VehicleType.BIKE,20);
    }
}
