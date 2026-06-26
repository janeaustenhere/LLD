package com.example.uber.model;

import com.example.uber.enums.ProductType;
import lombok.Data;

@Data
public class Vehicle {

    String licenseNumber;
    ProductType productType;
}
