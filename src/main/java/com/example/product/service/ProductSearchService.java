package com.example.product.service;

import com.example.product.model.Product;

import java.util.List;

public interface ProductSearchService {

    void indexProduct(Product product);

    List<Product> searchProducts(String query);
}
