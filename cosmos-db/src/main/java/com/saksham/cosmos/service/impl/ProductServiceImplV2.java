package com.saksham.cosmos.service.impl;

import com.azure.spring.data.cosmos.core.CosmosTemplate;
import com.azure.spring.data.cosmos.exception.CosmosAccessException;
import com.saksham.cosmos.entity.Product;
import com.saksham.cosmos.service.ProductServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplV2 implements ProductServiceV2 {

    @Autowired
    private CosmosTemplate cosmosTemplate;

    private static final String CONTAINER_NAME = "products";

    @Override
    public Product saveProduct(Product product) {
        try {
            Product savedProduct = cosmosTemplate.insert(CONTAINER_NAME, product);
            System.out.println("Product saved: " + savedProduct);
            return savedProduct;
        } catch (CosmosAccessException e) {
            throw new RuntimeException("Error saving product: " + e.getMessage(), e);
        }
    }

    @Override
    public Product updateProduct(String id, Product newProduct) throws Exception {
        try {
            Product existingProduct = cosmosTemplate.findById(id, Product.class);
            if (existingProduct == null) {
                throw new Exception("Product with id " + id + " does not exist");
            }

            existingProduct.setName(newProduct.getName());
            existingProduct.setDescription(newProduct.getDescription());
            existingProduct.setPrice(newProduct.getPrice());

            cosmosTemplate.upsert(CONTAINER_NAME, existingProduct);
            return existingProduct;
        } catch (CosmosAccessException e) {
            throw new RuntimeException("Error updating product: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            Iterable<Product> products = cosmosTemplate.findAll(Product.class);
            List<Product> productList = new ArrayList<>();
            products.forEach(productList::add);
            return productList;
        } catch (CosmosAccessException e) {
            throw new RuntimeException("Error retrieving all products: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Product> getProductById(String id) {
        try {
            Product product = cosmosTemplate.findById(id, Product.class);
            return Optional.ofNullable(product);
        } catch (CosmosAccessException e) {
            throw new RuntimeException("Error retrieving product with id " + id + ": " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteProduct(String id) {
        try {
            Product existingProduct = cosmosTemplate.findById(id, Product.class);
            // Delete the item by its id and partition key
            cosmosTemplate.deleteEntity(CONTAINER_NAME, existingProduct);
            System.out.println("Product with id " + id + " has been deleted successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Error deleting product with id " + id + ": " + e.getMessage(), e);
        }
    }


}

