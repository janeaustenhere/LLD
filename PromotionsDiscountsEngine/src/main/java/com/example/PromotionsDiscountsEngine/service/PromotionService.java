package com.example.PromotionsDiscountsEngine.service;


import com.example.PromotionsDiscountsEngine.model.Cart;
import com.example.PromotionsDiscountsEngine.promotion.PromotionEngine;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PromotionService {

    private final PromotionEngine promotionEngine;


    public PromotionService(PromotionEngine promotionEngine) {
        this.promotionEngine = promotionEngine;
    }

    public BigDecimal getDiscount(Cart cart){
       return promotionEngine.bestPossibleDiscount(cart);
    }
}
