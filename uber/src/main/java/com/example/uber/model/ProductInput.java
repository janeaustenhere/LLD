package com.example.uber.model;

import com.example.uber.enums.ProductType;
import lombok.Data;

@Data
public class ProductInput {


    double baseFare;
    double farePerHour;
    double farePerKM;
    int capacity;
    ProductType productType;
}
