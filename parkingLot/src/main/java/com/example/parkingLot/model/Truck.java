package com.example.parkingLot.model;

import com.example.parkingLot.enums.VehicleType;

public class Truck extends Vehicle{

    public Truck(VehicleInput vehicleInput){

        super(vehicleInput.getLicencePlate(), VehicleType.TRUCK,70);
    }
}
