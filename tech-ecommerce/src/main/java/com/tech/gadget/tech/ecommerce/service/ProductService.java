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

    // GET ALL PRODUCTS
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET PRODUCT BY ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // ADD PRODUCT
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
      //bulk products
    public List<Product> addProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }
    // UPDATE PRODUCT
    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setOldPrice(product.getOldPrice());
        existingProduct.setImageKey(product.getImageKey());
        existingProduct.setCategory(product.getCategory());
//        existingProduct.setSale(product.isSale());
        existingProduct.setStock(product.getStock());

        return productRepository.save(existingProduct);
    }

    // DELETE PRODUCTs
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);
    }
}
