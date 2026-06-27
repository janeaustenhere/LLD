package com.example.uber.model;

import com.example.uber.enums.PaymentStatus;
import com.example.uber.enums.PaymentType;
import com.example.uber.enums.RideStatus;
import lombok.Data;


@Data
public class Ride {

    String rideId;
    String userId;
    Location pickUpLocation;
    Location dropLocation;
    double amount;
    RideStatus rideStatus;
    Driver driver;
    PaymentType paymentType;
    PaymentStatus paymentStatus;
    double estimatedDistance;
    long estimatedTime;
    String productName;
    String fareId;



}
