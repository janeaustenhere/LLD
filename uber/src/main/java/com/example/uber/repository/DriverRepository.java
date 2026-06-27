package com.example.uber.repository;

import com.example.uber.model.Driver;
import com.example.uber.model.Rider;
import com.example.uber.model.UserInput;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DriverRepository {

    Map<String, Driver> driverMap = new HashMap<>();

    public Driver save(Driver driver){

        driverMap.put(driver.getUserId(),driver);

        return driver;
    }

    public Driver getRiderById(String driverId){
        return driverMap.get(driverId);
    }

    public List<Driver> getAllDriverList(){
        return driverMap.values().stream().toList();
    }

    public Driver updateVehicle(Driver driver){

        Driver driverToUpdate = driverMap.get(driver.getUserId());
        driverToUpdate.setVehicle(driver.getVehicle());
        return driverToUpdate;

    }
}
