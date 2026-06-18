package com.example.parkingLot.services;

import com.example.parkingLot.model.ParkingFloor;
import com.example.parkingLot.model.ParkingLot;
import com.example.parkingLot.model.ParkingSlot;
import com.example.parkingLot.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }


    public ParkingLot createParkingLot(ParkingLot parkingLot){
        return parkingLotRepository.createParkingLot(parkingLot);

    }

    public ParkingFloor addParkingFloor(ParkingFloor parkingFloor, String parkingLotId){
        return parkingLotRepository.addParkingFloor(parkingFloor,parkingLotId);
    }

    public ParkingSlot addParkingSlot(ParkingSlot parkingSlot, int floorNumber, String parkingLotId){
        return parkingLotRepository.addParkingSlot(parkingSlot,floorNumber,parkingLotId);
    }

    public void updateParkingSlot(ParkingSlot parkingSlot, int floorNumber,  String parkingLotId) {
      parkingLotRepository.updateParkingSlot(parkingSlot,floorNumber,parkingLotId);
    }

    public ParkingLot getParkingLotDetails(String parkingLotId){
        return parkingLotRepository.getParkingLotDetails(parkingLotId);
    }




}
