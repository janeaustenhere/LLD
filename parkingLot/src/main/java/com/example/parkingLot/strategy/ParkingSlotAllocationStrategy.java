package com.example.parkingLot.strategy;

import com.example.parkingLot.model.ParkingSlot;
import com.example.parkingLot.model.Vehicle;

import java.util.List;

public interface ParkingSlotAllocationStrategy {

    ParkingSlot allocateSlotToVehicle(List<ParkingSlot> parkingSlotList, Vehicle vehicle);
}
