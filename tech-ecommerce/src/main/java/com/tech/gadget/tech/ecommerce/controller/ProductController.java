package com.tech.gadget.tech.ecommerce.controller;
	import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.gadget.tech.ecommerce.entity.Product;
import com.tech.gadget.tech.ecommerce.service.ProductService;


	@RestController
	@RequestMapping("/api/products")
	@CrossOrigin(origins = "http://localhost:3000")
	
public class ProductController {
	    private final ProductService productService;

	    public ProductController(ProductService productService) {
	        this.productService = productService;
	    }

	    // Get all products
	    @GetMapping
	    public List<Product> getAllProducts() {

	        return productService.getAllProducts();
	    }

	    // Get product by ID
	    @GetMapping("/{id}")
	    public Product getProductById(@PathVariable Long id) {

	        return productService.getProductById(id);
	    }
	}

