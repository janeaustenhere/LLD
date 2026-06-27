package com.example.uber.repository;

import com.example.uber.model.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    Map<String, Product> productMap = new HashMap<>();

    public Product save(Product product){

        productMap.put(product.getName(), product);
        return product;
    }

    public Product getProductById(String productName){

        return productMap.get(productName);
    }

    public List<Product> getAllProducts(){

        return productMap.values().stream().toList();
    }
}
