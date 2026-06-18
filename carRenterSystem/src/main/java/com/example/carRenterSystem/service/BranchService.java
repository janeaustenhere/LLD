package com.example.carRenterSystem.service;

import com.example.carRenterSystem.factory.VehicleFactory;
import com.example.carRenterSystem.model.Branch;
import com.example.carRenterSystem.model.Vehicle;
import com.example.carRenterSystem.model.VehicleInput;
import com.example.carRenterSystem.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final VehicleFactory vehicleFactory;


    public BranchService(BranchRepository branchRepository, VehicleFactory vehicleFactory) {
        this.branchRepository = branchRepository;
        this.vehicleFactory = vehicleFactory;
    }

    public Branch registerBranch(Branch branch){

        return branchRepository.registerBranch(branch);
    }

    public Branch getBranchById(String branchId){
        return branchRepository.getBranchById(branchId);
    }

    public List<Branch> getAllBranches(){

        return branchRepository.getAllBranch();
    }

    public Branch deleteBranch(String branchId){

        return branchRepository.deleteBranch(branchId);
    }

    public void addVehicle(VehicleInput vehicle, String branchId){
        Vehicle createdVehicle = vehicleFactory.createVehicle(vehicle);
        branchRepository.addVehicle(createdVehicle,branchId);
    }

    public void removeVehicle( String  vehicleId, String branchId){
        branchRepository.removeVehicle(vehicleId,branchId);
    }
}
