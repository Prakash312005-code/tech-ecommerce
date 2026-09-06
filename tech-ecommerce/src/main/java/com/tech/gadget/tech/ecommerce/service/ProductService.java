package com.tech.gadget.tech.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tech.gadget.tech.ecommerce.entity.Product;
import com.tech.gadget.tech.ecommerce.repository.ProductRepository;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}