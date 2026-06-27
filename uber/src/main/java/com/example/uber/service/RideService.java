package com.example.uber.service;

import com.example.uber.model.*;
import com.example.uber.repository.FareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RideService {

    private final FareCalculationService fareCalculationService;
    private final ProductService productService;
    private final FareRepository fareRepository;
    private final DriverMatchingService driverMatchingService;

    public RideService(FareCalculationService fareCalculationService, ProductService productService, FareRepository fareRepository,
                       DriverMatchingService driverService) {
        this.fareCalculationService = fareCalculationService;
        this.productService = productService;
        this.fareRepository = fareRepository;
        this.driverMatchingService = driverService;
    }


    public Map<String, EstimatedFareAndTime> getEstimateFareAndTime(Ride ride) {
        List<Product> productList = productService.getAllProducts();
        return fareCalculationService.calculateEstimatedFareForRide(ride, productList);
    }

    public Ride requestRide(Ride ride) throws Exception {
        Fare fare = fareCalculationService.createFareObjectForRide(ride);
        fareRepository.save(fare);
        ride.setFareId(fare.getFareId());
        List<Driver> availableDriverList = driverMatchingService.getNearestAvailableDriverList(ride);
        if (availableDriverList.isEmpty()) {
            System.out.println("No available driver");
            throw new Exception("No available driver");
        }

        return confirmRide(ride, availableDriverList);

    }

    public Ride confirmRide(Ride ride, List<Driver> availableDriverList) throws Exception {
        Driver driver = driverMatchingService.assignDriverToRide(ride, availableDriverList);
        ride.setDriver(driver);
        return ride;
    }
}
