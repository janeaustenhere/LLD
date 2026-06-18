package com.example.parkingLot.model;

import com.example.parkingLot.enums.VehicleType;
import lombok.Data;

@Data
public class VehicleInput {

    String licencePlate;
    VehicleType vehicleType;
}
