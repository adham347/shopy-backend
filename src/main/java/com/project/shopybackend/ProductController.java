package com.project.shopybackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // add product
    @PostMapping
    public String addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return "Product added successfully";
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable String productId) {
        return productService.getProductById(productId);
    }

    // Update an existing product
    @PutMapping("/{productId}")
    public void updateProduct(@PathVariable String productId, @RequestBody Product product) {
        productService.editProduct(productId, product);
    }

    // Delete a product
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
    }
}
