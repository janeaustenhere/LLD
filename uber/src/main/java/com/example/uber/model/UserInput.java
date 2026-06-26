package com.example.uber.model;

import com.example.uber.enums.UserType;
import lombok.Data;

@Data
public class UserInput {

    String userId;
    String userName;
    double rating;
    String email;
    String contactNumber;
    UserType userType;
    Vehicle vehicle;
    DrivingLicense drivingLicense;
}
