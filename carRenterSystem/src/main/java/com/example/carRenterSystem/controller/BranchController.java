package com.example.carRenterSystem.controller;

import com.example.carRenterSystem.model.Branch;
import com.example.carRenterSystem.model.Vehicle;
import com.example.carRenterSystem.model.VehicleInput;
import com.example.carRenterSystem.service.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {


    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping("/registerBranch")
    public ResponseEntity<String> registerBranch(@RequestBody Branch branch){
        Branch createdBranch = null;
        try {
            createdBranch = branchService.registerBranch(branch);
        }catch (Exception ex){

           return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Branch registration failed");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Branch registered with branchId:: " + createdBranch.getBranchId());



    }

    @GetMapping("/getBranchDetails/{branchId}")
    public ResponseEntity<Branch> getBranchId(@PathVariable String branchId){
        Branch branch = null;

        try {
            branch = branchService.getBranchById(branchId);
        } catch (Exception e) {
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(branch);
        }

        return ResponseEntity.status(HttpStatus.OK).body(branch);

    }

    @GetMapping("/getAllBranches")
    public ResponseEntity<List<Branch>> getAllBranches(){
        List<Branch> branchList = new ArrayList<>();

        try {
            branchList = branchService.getAllBranches();
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(branchList);
        }

        return ResponseEntity.status(HttpStatus.OK).body(branchList);

    }

    @PostMapping("/addVehicleToBranch/{branchId}")
    public ResponseEntity<String> addVehicleToBranch(@RequestBody VehicleInput vehicle, @PathVariable String branchId){

        try {
            branchService.addVehicle(vehicle,branchId);
        } catch (Exception e) {

           return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Vehicle not added");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Vehicle added to branch");


    }

    @DeleteMapping("/deleteVehicle")
    public ResponseEntity<String> removeVehicleFromBranch(@RequestParam String vehicleId, @RequestParam String branchId){

        try {
            branchService.removeVehicle(vehicleId,branchId);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Vehicle not removed");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Vehicle removed from branch");
    }


}
