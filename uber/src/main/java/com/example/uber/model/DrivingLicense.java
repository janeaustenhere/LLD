package com.example.uber.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DrivingLicense {

    String encryptedDrivingLicense;
    LocalDate expiryDate;


}
