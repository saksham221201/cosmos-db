package com.saksham.cosmos.service;

import com.saksham.cosmos.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceV2 {
    Product saveProduct(Product product);
    Product updateProduct(String id, Product newProduct) throws Exception;
    List<Product> getAllProducts();
    Optional<Product> getProductById(String id);
    void deleteProduct(String id);
}
