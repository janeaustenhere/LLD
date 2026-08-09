package com.example.PromotionsDiscountsEngine.controller;


import com.example.PromotionsDiscountsEngine.model.Cart;
import com.example.PromotionsDiscountsEngine.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/totalAmount")
    public ResponseEntity<BigDecimal> getTotalAmountWithDiscount(@RequestBody Cart cart){

       BigDecimal amount =  cartService.getFinalTotalAmount(cart);

       return ResponseEntity.status(HttpStatus.OK).body(amount);



    }

}
