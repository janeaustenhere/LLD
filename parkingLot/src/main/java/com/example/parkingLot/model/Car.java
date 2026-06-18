package com.example.parkingLot.model;

import com.example.parkingLot.enums.VehicleType;
import lombok.Data;

import java.util.UUID;

@Data
public class Car extends Vehicle{

    public Car(VehicleInput vehicleInput){

        super(vehicleInput.getLicencePlate(), VehicleType.CAR,30);
    }
}
