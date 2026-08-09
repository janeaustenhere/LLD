package com.example.PromotionsDiscountsEngine.promotion;


import com.example.PromotionsDiscountsEngine.model.Cart;
import com.example.PromotionsDiscountsEngine.strategy.PromotionStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class PromotionEngine {

    private final Map<String,PromotionStrategy> activePromotions = new ConcurrentHashMap<>();
    private final List<PromotionStrategy> promotionStrategyList;

    public PromotionEngine(List<PromotionStrategy> promotionStrategyList) {
        this.promotionStrategyList = promotionStrategyList;

        for(PromotionStrategy promotionStrategy: promotionStrategyList){
            activePromotions.put(promotionStrategy.getPromotionId(),promotionStrategy);
        }
    }

    public void removePromotionS(String promotionId){
        activePromotions.remove(promotionId);
    }

    public void deployPromotions(String promotionId){
        PromotionStrategy promotionStrategy = promotionStrategyList.stream()
                .filter(promotionStrategy1 -> promotionStrategy1.getPromotionId()
                        .equals(promotionId)).findFirst().get();
        activePromotions.put(promotionId,promotionStrategy);
    }

    public BigDecimal bestPossibleDiscount(Cart cart){

        return activePromotions.values().stream()
                .map(promotionStrategy -> promotionStrategy.calculatePromotionDiscount(cart))

                .max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }
}
