package com.tech.gadget.tech.ecommerce.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.gadget.tech.ecommerce.entity.Product;
import com.tech.gadget.tech.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
        @CrossOrigin(origins = {
	    "http://localhost:3000",
	    "https://tech-gadgets-store-psi.vercel.app"
	})
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET ALL PRODUCTS
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET PRODUCT BY ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // ADD PRODUCT
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
	   // ADD PRODUCT bulk
    @PostMapping("/bulk")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return productService.addProducts(products);
    }
	 // UPDATE PRODUCT
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    // DELETE PRODUCTs
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
        return "Product deleted successfully";
    }
}
