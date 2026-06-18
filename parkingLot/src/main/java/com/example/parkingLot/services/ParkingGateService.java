package com.example.parkingLot.services;

import com.example.parkingLot.enums.PaymentMode;
import com.example.parkingLot.enums.PaymentStatus;
import com.example.parkingLot.factory.VehicleFactory;
import com.example.parkingLot.model.*;
import com.example.parkingLot.strategy.ParkingSlotAllocationStrategy;
import com.example.parkingLot.strategy.PricingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingGateService {

    private final TicketService ticketService;
    private final ParkingLotService parkingLotService;
    private final VehicleFactory vehicleFactory;
    private final ParkingSlotAllocationStrategy parkingSlotAllocationStrategy;
    private final PricingStrategy pricingStrategy;

    public ParkingGateService(TicketService ticketService, ParkingLotService parkingLotService,
                              VehicleFactory vehicleFactory,
                              ParkingSlotAllocationStrategy parkingSlotAllocationStrategy,
                              @Qualifier("timeBasedPricingStrategy") PricingStrategy pricingStrategy) {
        this.ticketService = ticketService;
        this.parkingLotService = parkingLotService;
        this.vehicleFactory = vehicleFactory;
        this.parkingSlotAllocationStrategy = parkingSlotAllocationStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public Ticket parkVehicle(VehicleInput vehicleInput, String parkingLotId, int parkingFloorNumber){

        Vehicle vehicle = vehicleFactory.createVehicle(vehicleInput);
        Optional<ParkingFloor> parkingFloor =  parkingLotService.getParkingLotDetails(parkingLotId).getParkingFloorList()
                .stream().filter(parkingFloorIn -> parkingFloorIn.getFloorNumber() == parkingFloorNumber).findFirst();

        if(parkingFloor.isEmpty()){
            throw new RuntimeException("parking floor not available");

        }

        List<ParkingSlot> parkingSlotList = parkingFloor.get().getParkingSlotList();
        ParkingSlot parkingSlot = parkingSlotAllocationStrategy.allocateSlotToVehicle(parkingSlotList,vehicle);
        if(parkingSlot == null){
            throw new RuntimeException("No slot available");
        }

        Ticket ticket = ticketService.createTicket(vehicle);

        return ticket;

     }

    public Ticket unParkVehicle(String ticketId){

        Ticket ticket = ticketService.getTicketDetails(ticketId);

        double amount = pricingStrategy.calculatePrice(ticket.getVehicle(), ticket.getEntryTime(), LocalDateTime.now());
        ticket.setAmountToPay(amount);

        return ticket;


    }


}
