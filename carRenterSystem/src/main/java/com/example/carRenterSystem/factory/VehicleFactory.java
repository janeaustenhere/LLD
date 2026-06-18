package com.example.carRenterSystem.factory;


import com.example.carRenterSystem.enums.VehicleType;
import com.example.carRenterSystem.model.SUV;
import com.example.carRenterSystem.model.Sedan;
import com.example.carRenterSystem.model.Vehicle;
import com.example.carRenterSystem.model.VehicleInput;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class VehicleFactory {

    public Vehicle createVehicle(VehicleInput vehicle){

        String vehicleType = vehicle.getVehicleType();
        String vehicleId = UUID.randomUUID().toString();
        vehicle.setVehicleId(vehicleId);
        if(vehicleType.equals(String.valueOf(VehicleType.SUV))){
            return new SUV(vehicle);
        }else if(vehicleType.equals(String.valueOf(VehicleType.SEDAN))){
            return new Sedan(vehicle);
        }

        return null;

    }
}
