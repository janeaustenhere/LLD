package com.example.uber.service;

import com.example.uber.model.Driver;
import com.example.uber.model.Ride;
import com.example.uber.repository.DriverRepository;
import com.example.uber.repository.FareRepository;
import com.example.uber.strategy.DriverAssignmentStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverMatchingService {

    private final DriverRepository driverRepository;
    private final DriverAssignmentStrategy driverAssignmentStrategy;
    private final FareRepository fareRepository;

    public DriverMatchingService(DriverRepository driverRepository,
                                 @Qualifier("highestRatedDriverAssignmentStrategy") DriverAssignmentStrategy driverAssignmentStrategy, FareRepository fareRepository) {
        this.driverRepository = driverRepository;
        this.driverAssignmentStrategy = driverAssignmentStrategy;
        this.fareRepository = fareRepository;
    }

    public List<Driver> getNearestAvailableDriverList(Ride ride){
        return driverRepository.getAllDriverList().stream()
                .filter(driver -> driver.getIsAvailable() != null && driver.getIsAvailable().get())
                .filter(driver -> driver.getVehicle().getProductType().name().equals(ride.getProductName()))
                .filter(driver -> driver.getCurrentLocation().getDistance(ride.getPickUpLocation()) <= 50)
                .collect(Collectors.toList());

    }

    public Driver assignDriverToRide(Ride ride, List<Driver> availableDriverList) throws Exception {
        if(fareRepository.getFareById(ride.getFareId()).getExpireTime() < System.currentTimeMillis()){
            throw new Exception("Fare has changed, select again");
        }
        return driverAssignmentStrategy.assignDriver(ride,availableDriverList);
    }
}
