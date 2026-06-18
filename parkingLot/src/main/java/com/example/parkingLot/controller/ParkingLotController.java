package com.example.parkingLot.controller;

import com.example.parkingLot.model.ParkingFloor;
import com.example.parkingLot.model.ParkingLot;
import com.example.parkingLot.model.ParkingSlot;
import com.example.parkingLot.services.ParkingLotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkingLot")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }


    @PostMapping("/createParkingLot")
    public ResponseEntity<String> createParkingLot(@RequestBody ParkingLot parkingLot){
        ParkingLot createdParkingLot = null;
        try {
            parkingLotService.createParkingLot(parkingLot);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Parking lot not create");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("ParkingLot created with id: " + parkingLot.getParkingLotId());

    }

    @PostMapping("/addParkingFloor/{parkingLotId}")
    public ResponseEntity<String> addParkingFloor(@RequestBody ParkingFloor parkingFloor,
                                                  @PathVariable String parkingLotId){

        ParkingFloor parkingFloorAdded = null;
        try{
            parkingFloorAdded = parkingLotService.addParkingFloor(parkingFloor,parkingLotId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Parking floor not added");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Parking floor added");

    }

    @PostMapping("/addParkingSlot")
    public ResponseEntity<String> addParkingSlot(@RequestBody ParkingSlot parkingSlot,
                                                 @RequestParam Integer parkingFloorNumber,
                                                 @RequestParam String parkingLotId){

        ParkingSlot parkingSlotAdded = null;

        try{
            parkingSlotAdded = parkingLotService.addParkingSlot(parkingSlot,parkingFloorNumber,parkingLotId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Parking slot not added");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Parking slot added");
    }

    @PutMapping("/updateParkingSlot")
    public ResponseEntity<String> updateParkingSlot(@RequestBody ParkingSlot parkingSlot,
                                                    @RequestParam Integer parkingFloorNumber,
                                                    @RequestParam String parkingLotId){

        try{
            parkingLotService.updateParkingSlot(parkingSlot,parkingFloorNumber,parkingLotId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Parking slot not updated");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Parking slot updated");
    }

    @GetMapping("/getParkingLotDetails/{parkingLotId}")
    public ResponseEntity<ParkingLot> getParkingLotDetails(@PathVariable String parkingLotId){

        ParkingLot parkingLot = null;

        try{
            parkingLot = parkingLotService.getParkingLotDetails(parkingLotId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(parkingLot);
    }


}
