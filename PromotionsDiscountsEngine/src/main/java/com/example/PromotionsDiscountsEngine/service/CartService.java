package com.example.PromotionsDiscountsEngine.service;


import com.example.PromotionsDiscountsEngine.model.Cart;
import com.example.PromotionsDiscountsEngine.model.CartItem;
import com.example.PromotionsDiscountsEngine.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final PromotionService promotionService;

    public CartService(CartRepository cartRepository, PromotionService promotionService) {
        this.cartRepository = cartRepository;
        this.promotionService = promotionService;
    }

    public void addCartItem(String userId, CartItem cartItem){
        cartRepository.addItem(userId,cartItem);
    }

    public void removeCartItem(String userId, CartItem cartItem){
            cartRepository.removeCartItem(userId,cartItem);
    }

    public BigDecimal getFinalTotalAmount(Cart cart){
        BigDecimal totalAmount = cart.getCartItemList()
                .stream().map(cartItem -> cartItem.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                        .reduce(BigDecimal.ZERO,BigDecimal::add);


        BigDecimal discount = promotionService.getDiscount(cart);
        System.out.println("totalAmount:: " + totalAmount);
        return totalAmount.subtract(discount);


    }



}
