package com.example.elevatorSystem.controller;


import com.example.elevatorSystem.enums.Direction;
import com.example.elevatorSystem.model.Elevator;
import com.example.elevatorSystem.model.Request;
import com.example.elevatorSystem.services.ElevatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/elevator")
public class ElevatorController {

    private final ElevatorService elevatorService;

    public ElevatorController(ElevatorService elevatorService) {
        this.elevatorService = elevatorService;
    }


    @PostMapping("/addElevator")
    public ResponseEntity<String> addElevator(@RequestBody Elevator elevator){

        elevatorService.addElevator(elevator);

        return ResponseEntity.status(HttpStatus.CREATED).body("Elevator added");

    }

    @PostMapping("/run")
    public ResponseEntity<String> runElevators(){
            elevatorService.runElevators();

            return ResponseEntity.status(HttpStatus.OK).body("Elevators started");
    }

    @PostMapping("/externalRequests/{floor}/{direction}")
    public ResponseEntity<String> initiateExternalRequest
            (@PathVariable Integer floor, @PathVariable Direction direction){

        elevatorService.addExternalRequests(new Request(UUID.randomUUID().toString(),floor,direction));

        return ResponseEntity.status(HttpStatus.OK).body("Request Sent");

    }

    @PostMapping("/internalRequest/{elevatorId}/{floor}")
    public ResponseEntity<String> initiateInternalRequest(@PathVariable Integer elevatorId,
                                                          @PathVariable Integer floor) throws Exception {

        Optional<Elevator> elevatorOptional = elevatorService.getElevatorList()
                .stream().filter(elevator1 -> elevator1.getElevatorId() == elevatorId).findFirst();
        if(elevatorOptional.isEmpty()){
            throw new Exception("Invalid request");
        }

        Elevator elevator = elevatorOptional.get();
        Direction direction = (elevator.getCurrentFloor() > floor) ? Direction.UP : Direction.DOWN;
        elevatorService.addInternalRequests(elevatorId, new Request(UUID.randomUUID().toString(),floor,direction));

        return ResponseEntity.status(HttpStatus.OK).body("Request Sent");

    }

}
