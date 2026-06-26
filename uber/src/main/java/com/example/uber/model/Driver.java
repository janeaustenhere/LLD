package com.example.uber.model;

import com.example.uber.enums.UserType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Driver extends User {

    Vehicle vehicle;
    Location currentLocation;
    DrivingLicense drivingLicense;

    @Override
    UserType getUserType() {
        return UserType.DRIVER;
    }
}
