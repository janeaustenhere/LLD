package com.example.carRenterSystem.repository;

import com.example.carRenterSystem.enums.VehicleStatus;
import com.example.carRenterSystem.enums.VehicleType;
import com.example.carRenterSystem.model.Branch;
import com.example.carRenterSystem.model.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class BranchRepository {

    Map<String, Branch> branchMap = new HashMap<>();

    public Branch registerBranch(Branch branch){
        String branchId = UUID.randomUUID().toString();
        branch.setBranchId(branchId);
        branchMap.put(branchId, branch);
        return branch;
    }

    public Branch getBranchById(String branchId){

        if(!branchMap.containsKey(branchId)){
            throw new RuntimeException("Branch id not present in database");
        }

        return branchMap.get(branchId);
    }

    public List<Branch> getAllBranch() {

        return branchMap.values().stream().toList();
    }

    public Branch deleteBranch(String branchId){

        if(!branchMap.containsKey(branchId)){
            throw new RuntimeException("Branch id not present in database");
        }

        return branchMap.remove(branchId);
    }

    public void addVehicle(Vehicle vehicle, String branchId){

        VehicleType vehicleType = vehicle.getVehicleType();
        vehicle.getIsAvailable().set(true);
        vehicle.setVehicleStatus(VehicleStatus.AVAILABLE);
        Branch branch = branchMap.get(branchId);
        branch.getVehicleMap().
                computeIfAbsent(vehicleType, vehicleList -> new ArrayList<>())
                .add(vehicle);
    }

    public void removeVehicle( String vehicleId, String branchId){

        Branch branch = branchMap.get(branchId);

        branch.getVehicleMap().values().forEach(list ->
                list.removeIf(vehicle1 ->
                        vehicle1.getVehicleId().equals(vehicleId)));
    }


}
