package com.example.PromotionsDiscountsEngine.repository;


import com.example.PromotionsDiscountsEngine.model.CartItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class CartRepository {

    Map<String, List<CartItem>> cartMap = new ConcurrentHashMap<>();

    public void addItem(String userId, CartItem cartItem){

        cartMap.computeIfAbsent(userId,cart -> new CopyOnWriteArrayList<>()).add(cartItem);
    }

    public List<CartItem> getCartItemList(String userId){

        return cartMap.get(userId);
    }

    public void  removeCartItem(String userId, CartItem cartItem){

        cartMap.get(userId).removeIf(cartItem1 -> cartItem1.getProduct().getId().equals(cartItem.getProduct().getId()));
    }

}
