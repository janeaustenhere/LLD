package com.example.PromotionsDiscountsEngine.model;


import lombok.Data;

import java.util.List;

@Data
public class Cart {

    String userId;
    List<CartItem> cartItemList;
}
