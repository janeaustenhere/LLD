package com.example.PromotionsDiscountsEngine.model;

import com.example.PromotionsDiscountsEngine.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    String id;
    String name;
    BigDecimal price;
    Category category;
}
