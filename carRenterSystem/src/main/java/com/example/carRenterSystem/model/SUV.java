package com.example.carRenterSystem.model;

import com.example.carRenterSystem.enums.VehicleType;
import lombok.Data;

import java.util.UUID;

@Data
public class SUV extends Vehicle{

    public SUV(VehicleInput vehicle){

        super(vehicle.getVehicleId(),vehicle.getLicensePlate(),
                vehicle.getModel(), VehicleType.SUV, vehicle.getPricePerHour(), vehicle.getPricePerKm());
    }


}
