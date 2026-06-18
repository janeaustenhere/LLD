package com.example.parkingLot.strategy;

import com.example.parkingLot.enums.ParkingSlotStatus;
import com.example.parkingLot.model.ParkingSlot;
import com.example.parkingLot.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AllocateFirstFreeSlots implements ParkingSlotAllocationStrategy{
    @Override
    public ParkingSlot  allocateSlotToVehicle(List<ParkingSlot> parkingSlotList, Vehicle vehicle) {
        List<ParkingSlot> availableList = parkingSlotList.stream().
                filter(parkingSlot -> parkingSlot.getParkingSlotStatus()
                        .equals(ParkingSlotStatus.AVAILABLE)).toList();

        for(ParkingSlot slot: availableList){

            if(slot.getIsSlotOccupied().compareAndSet(false,true)){
                return slot;
            }
        }

        return null;
    }
}
