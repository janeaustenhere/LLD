package com.example.uber.service;

import com.example.uber.factory.UserObjectCreateFactory;
import com.example.uber.model.Driver;
import com.example.uber.model.Ride;
import com.example.uber.model.UserInput;
import com.example.uber.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final UserObjectCreateFactory userObjectCreateFactory;

    public DriverService(DriverRepository driverRepository, UserObjectCreateFactory userObjectCreateFactory) {
        this.driverRepository = driverRepository;
        this.userObjectCreateFactory = userObjectCreateFactory;
    }

    public Driver addDriver(UserInput userInput){

        Driver driver = (Driver) userObjectCreateFactory.createUserObject(userInput);
        driverRepository.save(driver);
        return driver;
    }

    public Driver getDriverById(String driverId){

       return driverRepository.getRiderById(driverId);
    }

    public List<Driver> getAllDrivers(){

        return driverRepository.getAllDriverList();
    }

    public Driver updateVehicle (UserInput userInput){

        Driver driver = (Driver) userObjectCreateFactory.createUserObject(userInput);
        driverRepository.updateVehicle(driver);
        return driver;
    }




}
