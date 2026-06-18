package com.example.amazonLocker.controller;


import com.example.amazonLocker.models.Locker;
import com.example.amazonLocker.services.LockerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locker")
public class LockerController {

    private final LockerService lockerService;

    public LockerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }


    @PostMapping("/registerLocker")
    ResponseEntity<String> registerLocker(@RequestParam String zipcode , @RequestBody Locker locker){
        lockerService.registerLocker(zipcode,locker);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Locker register successfully");

    }



}
