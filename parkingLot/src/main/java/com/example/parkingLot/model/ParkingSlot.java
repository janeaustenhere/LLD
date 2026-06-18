package com.example.parkingLot.model;

import com.example.parkingLot.enums.ParkingSlotStatus;
import com.example.parkingLot.enums.VehicleType;
import lombok.Data;

import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class ParkingSlot {

    String parkingSlotId;
    VehicleType vehicleType;
    ParkingSlotStatus parkingSlotStatus = ParkingSlotStatus.AVAILABLE;
    AtomicBoolean isSlotOccupied = new AtomicBoolean(false);

}
