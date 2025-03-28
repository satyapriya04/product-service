package com.example.product.service;

import com.example.product.model.Product;
import org.springframework.validation.annotation.Validated;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> getAllProducts();

}
