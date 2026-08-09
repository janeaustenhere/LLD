package com.example.PromotionsDiscountsEngine.strategy;

import com.example.PromotionsDiscountsEngine.model.Cart;

import java.math.BigDecimal;

public interface PromotionStrategy {

    String getPromotionId();
    BigDecimal calculatePromotionDiscount(Cart cart);
}
