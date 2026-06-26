package com.example.uber.model;

import com.example.uber.enums.ProductType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Product {

    double baseFare;
    double farePerHour;
    double farePerKM;
    int capacity;

    abstract ProductType getProductType();

}
