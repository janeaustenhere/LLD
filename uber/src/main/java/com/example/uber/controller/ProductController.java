package com.example.uber.controller;

import com.example.uber.model.Product;
import com.example.uber.model.ProductInput;
import com.example.uber.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductInput productInput){

        Product product = null;
        try{
            product = productService.addProduct(productInput);
        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(product);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/getProduct/{productName}")
    public ResponseEntity<Product> getProductById(@PathVariable String productName){

        Product product = null;
        try {
            product = productService.getProductById(productName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(product);
        }

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getAllProducts(){

        List<Product> products = new ArrayList<>();
        try {
            products = productService.getAllProducts();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(products);
        }

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }
}
