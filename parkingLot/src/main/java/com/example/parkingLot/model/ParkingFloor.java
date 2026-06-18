package com.example.parkingLot.model;

import com.example.parkingLot.enums.ParkingFloorStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParkingFloor {

    String parkingFloorId;
    int floorNumber;
    List<ParkingSlot> parkingSlotList = new ArrayList<>();
    ParkingFloorStatus parkingFloorStatus = ParkingFloorStatus.AVAILABLE;
}
