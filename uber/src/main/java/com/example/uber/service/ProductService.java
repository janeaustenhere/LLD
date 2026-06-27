package com.example.uber.service;

import com.example.uber.factory.ProductObjectCreateFactory;
import com.example.uber.model.Product;
import com.example.uber.model.ProductInput;
import com.example.uber.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductObjectCreateFactory productObjectCreateFactory;


    public ProductService(ProductRepository productRepository, ProductObjectCreateFactory productObjectCreateFactory) {
        this.productRepository = productRepository;
        this.productObjectCreateFactory = productObjectCreateFactory;
    }

    public Product addProduct(ProductInput productInput){

        Product product = productObjectCreateFactory.createProductObject(productInput);
        productRepository.save(product);
        return product;
    }

    public Product getProductById(String productName){
       return productRepository.getProductById(productName);
    }

    public List<Product> getAllProducts(){

        return productRepository.getAllProducts();
    }


}
