package com.example.uber.model;

import com.example.uber.enums.UserType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.concurrent.atomic.AtomicBoolean;

@Data
@SuperBuilder
public class Driver extends User {

    Vehicle vehicle;
    Location currentLocation;
    DrivingLicense drivingLicense;
    AtomicBoolean isAvailable = new AtomicBoolean(true);

    @Override
    UserType getUserType() {
        return UserType.DRIVER;
    }
}
