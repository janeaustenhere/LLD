package com.example.PromotionsDiscountsEngine.strategy;

import com.example.PromotionsDiscountsEngine.Category;
import com.example.PromotionsDiscountsEngine.model.Cart;
import com.example.PromotionsDiscountsEngine.model.CartItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BulkDiscountPromotionStrategy implements PromotionStrategy {

    private final String promotionId;
    private final Category targetCategory;
    private final int minQuantity;
    private final double discountPercentage;

    public BulkDiscountPromotionStrategy() {
        this.promotionId = "BULK_DISCOUNT";
        this.targetCategory = Category.ELECTRONICS;
        this.minQuantity = 10;
        this.discountPercentage = 6;
    }

    @Override
    public String getPromotionId() {
        return this.promotionId;
    }

    @Override
    public BigDecimal calculatePromotionDiscount(Cart cart) {
        BigDecimal discount = BigDecimal.valueOf(0);
        for(CartItem cartItem : cart.getCartItemList()){
           if(cartItem.getQuantity() > minQuantity && targetCategory.equals(cartItem.getProduct().getCategory())){
               discount = discount.add((cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                       .multiply(BigDecimal.valueOf(discountPercentage).divide(BigDecimal.valueOf(100))));
            }
    }
      System.out.println("discount:: BULK_DISCOUNT:: " + discount);
        return discount;
    }
}
