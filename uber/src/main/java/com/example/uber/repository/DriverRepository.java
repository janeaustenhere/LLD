package com.example.uber.repository;

import com.example.uber.model.Driver;
import com.example.uber.model.Rider;
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
}
