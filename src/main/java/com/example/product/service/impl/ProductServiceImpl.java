package com.example.product.service.impl;

import com.example.product.entity.ProductEntity;
import com.example.product.exception.ProductException;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        try {
            ProductEntity productEntity = mapProductToEntity(product);
            ProductEntity saved = productRepository.save(productEntity);
            return mapProductEntityToProduct(saved);
        } catch (Exception ex) {
            log.error("Error saving product: {}", product, ex);
            throw new ProductException("Failed to save product", ex);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return productRepository.findAll().stream()
                    .map(this::mapProductEntityToProduct)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Error fetching products", ex);
            throw new ProductException("Failed to fetch products", ex);
        }
    }
    ProductEntity mapProductToEntity(Product product){
        return ProductEntity.builder().name(product.getName()).description(product.getDescription()).price(product.getPrice()).build();
    }

    Product mapProductEntityToProduct(ProductEntity productEntity){
        return Product.builder().productId(productEntity.getId()).name(productEntity.getName()).description(
                productEntity.getDescription()).price(productEntity.getPrice()).build();
    }

}
