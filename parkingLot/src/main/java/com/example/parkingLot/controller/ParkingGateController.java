package com.example.parkingLot.controller;


import com.example.parkingLot.model.Ticket;
import com.example.parkingLot.model.VehicleInput;
import com.example.parkingLot.services.ParkingGateService;
import com.example.parkingLot.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingGate")
public class ParkingGateController {

    private final ParkingGateService parkingGateService;

    private final TicketService ticketService;

    public ParkingGateController(ParkingGateService parkingGateService, TicketService ticketService) {
        this.parkingGateService = parkingGateService;
        this.ticketService = ticketService;
    }


    @PostMapping("/parkVehicle")
    ResponseEntity<Ticket> parkVehicle(@RequestBody VehicleInput vehicleInput,
                                       @RequestParam String parkingLotId,
                                       @RequestParam int parkingFloorNumber){
        Ticket ticket = null;
       try {
            ticket = parkingGateService.parkVehicle(vehicleInput, parkingLotId, parkingFloorNumber);
        } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ticket);
       }

       return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @GetMapping("/getTicketDetails/{ticketId}")
    ResponseEntity<Ticket> getTicketDetails(@PathVariable String ticketId){
        Ticket ticket = null;
        try {
            ticket = ticketService.getTicketDetails(ticketId);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ticket);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(ticket);

    }

    @PostMapping("/unParkVehicle/{ticketId}")
    ResponseEntity<Ticket> unParkVehicle(@PathVariable String ticketId){
        Ticket ticket = null;
        try {
            ticket = parkingGateService.unParkVehicle(ticketId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(ticket);
        }

        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }


}
