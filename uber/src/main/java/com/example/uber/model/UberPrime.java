package com.example.uber.model;

import com.example.uber.enums.ProductType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UberPrime extends Product{
    @Override
    ProductType getProductType() {
        return ProductType.UBER_PRIME;
    }
}
