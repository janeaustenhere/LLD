package com.example.uber.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class Fare {

    String fareId;
    Location sourceLocation;
    Location destinationLocation;
    String productName;
    double amount;
    long expireTime;

}
