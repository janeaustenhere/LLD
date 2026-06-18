package com.example.carRenterSystem.strategy;

import com.example.carRenterSystem.enums.VehicleStatus;
import com.example.carRenterSystem.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component("leastBookedVehicleStrategy")
public class LeastBookedVehicleStrategy implements BookingStrategy{
    @Override
    public Vehicle bookVehicle(List<Vehicle> vehicleList) {
        List<Vehicle> availableVehicleList = vehicleList.stream().
                filter(vehicle -> vehicle.getVehicleStatus().equals(VehicleStatus.AVAILABLE))
                .collect(Collectors.toUnmodifiableList());

        List<Vehicle> sortedList = availableVehicleList.stream().
                sorted(Comparator.comparing(Vehicle::getBookingCount)).collect(Collectors.toUnmodifiableList());

        for(Vehicle vehicle: sortedList){

            if(vehicle.getIsAvailable().compareAndSet(true,false)){
                return vehicle;
            }
        }

        return null;

    }
}
