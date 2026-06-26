package com.example.uber.model;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Fare {

    String fareId;
    Location sourceLocation;
    Location destinationLocation;
    String productId;
    double amount;
    LocalDateTime expireTime;

}
