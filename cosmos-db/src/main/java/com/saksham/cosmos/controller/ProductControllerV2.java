package com.saksham.cosmos.controller;

import com.saksham.cosmos.entity.Product;
import com.saksham.cosmos.service.ProductService;
import com.saksham.cosmos.service.ProductServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/products")
public class ProductControllerV2 {

    @Autowired
    private ProductServiceV2 productServiceV2;

    // Create product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        System.out.println("Request Body: " + product);
        Product savedProduct = productServiceV2.saveProduct(product);
        System.out.println("Saved Product: " + savedProduct);
        return ResponseEntity.ok(savedProduct);
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) throws Exception {
        Product updatedProduct = productServiceV2.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    // Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productServiceV2.getAllProducts();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Optional<Product> product = productServiceV2.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productServiceV2.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

