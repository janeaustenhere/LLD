package com.example.uber.factory;

import com.example.uber.model.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProductObjectCreateFactory {


   public Product createProductObject(ProductInput productInput){

       switch (productInput.getProductType()){

           case UBER_GO -> {
               return UberGO.builder()
                       .capacity(4)
                       .baseFare(23.00)
                       .farePerHour(21.00)
                       .farePerKM(3.0)
                       .name(productInput.getProductType().name())
                       .productId(UUID.randomUUID().toString())
                       .build();
           }

           case UBER_PRIME -> {
               return UberPrime.builder()
                       .capacity(4)
                       .baseFare(25.00)
                       .farePerKM(5.00)
                       .farePerHour(6.00)
                       .name(productInput.getProductType().name())
                       .productId(UUID.randomUUID().toString())
                       .build();
           }

           case UBER_SEDAN -> {

               return UberSedan.builder()
                       .capacity(4)
                       .baseFare(35.00)
                       .farePerKM(7.00)
                       .farePerKM(8.00)
                       .name(productInput.getProductType().name())
                       .productId(UUID.randomUUID().toString())
                       .build();
           }

           case UBER_XL -> {

               return UberXL.builder()
                       .capacity(6)
                       .farePerKM(8.00)
                       .farePerHour(10.00)
                       .baseFare(50.00)
                       .name(productInput.getProductType().name())
                       .productId(UUID.randomUUID().toString())
                       .build();
           }
       }

       return null;
   }
}
