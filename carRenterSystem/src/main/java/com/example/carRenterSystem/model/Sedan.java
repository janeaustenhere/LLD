package com.example.carRenterSystem.model;

import com.example.carRenterSystem.enums.VehicleType;
import lombok.Data;

@Data
public class Sedan extends Vehicle{

    public Sedan(VehicleInput vehicle){
        super(vehicle.getVehicleId(),vehicle.getLicensePlate(),
                vehicle.getModel(), VehicleType.SEDAN, vehicle.getPricePerHour(), vehicle.getPricePerKm());
    }



}
