package com.example.uber.controller;

import com.example.uber.model.Driver;
import com.example.uber.model.UserInput;
import com.example.uber.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping("/addDriver")
    public ResponseEntity<Driver> addDriver(@RequestBody UserInput userInput){
       Driver driver = null;
       try
       {
           driver = driverService.addDriver(userInput);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(driver);
       }

       return ResponseEntity.status(HttpStatus.CREATED).body(driver);
    }

    @GetMapping("/getDriver/{driverId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable String driverId){
        Driver driver = null;
        try
        {
            driver = driverService.getDriverById(driverId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(driver);
        }

        return ResponseEntity.status(HttpStatus.OK).body(driver);
    }

    @GetMapping("/getDrivers")
    public ResponseEntity<List<Driver>> getAllDrivers(){
        List<Driver> driverList = new ArrayList<>();
        try
        {
            driverList = driverService.getAllDrivers();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(driverList);
        }

        return ResponseEntity.status(HttpStatus.OK).body(driverList);
    }

    @PostMapping("/updateVehicle")
    public ResponseEntity<Driver> updateVehicle(@RequestBody UserInput userInput){
        Driver driver = null;
        try
        {
            driver = driverService.updateVehicle(userInput);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(driver);
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(driver);
    }
}
