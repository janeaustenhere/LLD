package com.example.uber.controller;

import com.example.uber.model.EstimatedFareAndTime;
import com.example.uber.model.Ride;
import com.example.uber.service.RideService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/ride")
public class RideController {

    private final RideService rideService;


    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/getEstimatedFareAndTime")
    public ResponseEntity<Map<String, EstimatedFareAndTime>> getEstimatedFareAndTime(@RequestBody Ride ride){
        Map<String, EstimatedFareAndTime> map = rideService.getEstimateFareAndTime(ride);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @PostMapping("/requestRide")
    public ResponseEntity<Ride> requestRide(@RequestBody Ride ride) throws Exception {
         Ride requestRide = rideService.requestRide(ride);
        return ResponseEntity.status(HttpStatus.OK).body(requestRide);
    }
}
