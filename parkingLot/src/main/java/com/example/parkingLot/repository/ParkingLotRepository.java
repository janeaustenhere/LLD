package com.example.parkingLot.repository;


import com.example.parkingLot.model.ParkingFloor;
import com.example.parkingLot.model.ParkingLot;
import com.example.parkingLot.model.ParkingSlot;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ParkingLotRepository {

    Map<String, ParkingLot> parkingLotMap = new HashMap<>();

    public ParkingLot createParkingLot(ParkingLot parkingLot){
        String parkingLotId = UUID.randomUUID().toString();
        parkingLot.setParkingLotId(parkingLotId);
        parkingLotMap.put(parkingLotId,parkingLot);
        return parkingLot;
    }

    public ParkingFloor addParkingFloor(ParkingFloor parkingFloor, String parkingLotId){
        String parkingFloorId = UUID.randomUUID().toString();
        parkingFloor.setParkingFloorId(parkingFloorId);
        parkingLotMap.get(parkingLotId).getParkingFloorList().add(parkingFloor);
        return parkingFloor;

    }

    public ParkingSlot addParkingSlot(ParkingSlot parkingSlot, int floorNumber,  String parkingLotId){
        Optional<ParkingFloor> parkingFloor = parkingLotMap.get(parkingLotId).getParkingFloorList().stream()
                .filter(parkingFloorIn -> parkingFloorIn.getFloorNumber() == floorNumber)
                .findFirst();
        if(parkingFloor.isEmpty()){
            throw  new RuntimeException("Parking floor not available");
        }
        parkingFloor.get().getParkingSlotList().add(parkingSlot);
        return parkingSlot;
    }

    public void updateParkingSlot(ParkingSlot parkingSlot, int floorNumber,  String parkingLotId){
        Optional<ParkingFloor> parkingFloor = parkingLotMap.get(parkingLotId).getParkingFloorList().stream()
                .filter(parkingFloorIn -> parkingFloorIn.getFloorNumber() == floorNumber)
                .findFirst();
        if(parkingFloor.isEmpty()){
            throw  new RuntimeException("Parking floor not available");
        }
        Optional<ParkingSlot> parkingSlotToUpdate = parkingFloor.get().getParkingSlotList().stream()
                .filter(parkingSlot1 -> parkingSlot1.getParkingSlotId().equals(parkingSlot.getParkingSlotId()))
                .findFirst();

        if(!parkingSlotToUpdate.isPresent()){
            throw new RuntimeException("Parking slot not present");
        }

        parkingSlotToUpdate.get().setParkingSlotStatus(parkingSlot.getParkingSlotStatus());
    }

    public ParkingLot getParkingLotDetails(String parkingLotId){
        return parkingLotMap.get(parkingLotId);
    }
}
