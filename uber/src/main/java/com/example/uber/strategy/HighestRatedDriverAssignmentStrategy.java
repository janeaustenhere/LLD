package com.example.uber.strategy;

import com.example.uber.model.Driver;
import com.example.uber.model.Ride;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

@Component("highestRatedDriverAssignmentStrategy")
public class HighestRatedDriverAssignmentStrategy implements DriverAssignmentStrategy{
    @Override
    public Driver assignDriver(Ride ride, List<Driver> availableDriverList) {
        List<Driver> sortedList = availableDriverList.stream()
                .sorted(Comparator.comparing(Driver::getRating).reversed())
                .toList();

        for(Driver driver : sortedList){

            if(driver.getIsAvailable().compareAndSet(true,false)){
                return driver;
            }
        }

        return null;
    }

}
