package com.example.parkingLot.factory;

import com.example.parkingLot.enums.VehicleType;
import com.example.parkingLot.model.*;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory {

    public Vehicle createVehicle(VehicleInput vehicleInput){

        return switch (vehicleInput.getVehicleType()) {
            case VehicleType.CAR -> new Car(vehicleInput);
            case VehicleType.BIKE -> new Bike(vehicleInput);
            case VehicleType.TRUCK -> new Truck(vehicleInput);
            default -> null;
        };

    }


}
