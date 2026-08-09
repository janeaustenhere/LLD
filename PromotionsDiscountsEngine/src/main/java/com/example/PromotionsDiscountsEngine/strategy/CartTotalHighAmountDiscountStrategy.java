package com.example.PromotionsDiscountsEngine.strategy;

import com.example.PromotionsDiscountsEngine.model.Cart;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CartTotalHighAmountDiscountStrategy implements PromotionStrategy{

    private final String promotionId;
    private final BigDecimal minTotal;
    private final BigDecimal discountPercentage;



    public CartTotalHighAmountDiscountStrategy() {
        this.discountPercentage = BigDecimal.valueOf(5);
        this.minTotal = BigDecimal.valueOf(250000);
        this.promotionId = "HIGH_TOTAL_DISCOUNT";

    }

    @Override
    public String  getPromotionId() {
        return this.promotionId;
    }

    @Override
    public BigDecimal calculatePromotionDiscount(Cart cart) {
      BigDecimal totalPrice = cart.getCartItemList().stream()
                .map(cartItem ->
                        cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
              .reduce(BigDecimal.ZERO,BigDecimal::add);

        System.out.println("HIGH_TOTAL_DISCOUNT:: totalPrice:: " + totalPrice);
        if(totalPrice.compareTo(minTotal) >= 0){
            BigDecimal discount =  totalPrice.multiply(discountPercentage.divide(BigDecimal.valueOf(100)));
            System.out.println("Discount:: HIGH_TOTAL_DISCOUNT " +discount );
            return discount;
        }

        return BigDecimal.valueOf(0);
    }
}
