package com.example.parkingLot.model;

import com.example.parkingLot.enums.ParkingLotStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParkingLot {

    String parkingLotId;
    String name;
    Address address;
    List<ParkingFloor> parkingFloorList = new ArrayList<>();
    ParkingLotStatus parkingLotStatus = ParkingLotStatus.OPEN;
}
