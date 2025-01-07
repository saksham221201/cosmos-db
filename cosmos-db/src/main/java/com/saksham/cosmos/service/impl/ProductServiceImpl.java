package com.saksham.cosmos.service.impl;

import com.azure.spring.data.cosmos.core.CosmosTemplate;
import com.saksham.cosmos.entity.Product;
import com.saksham.cosmos.repository.ProductRepository;
import com.saksham.cosmos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private CosmosTemplate cosmosTemplate;

    @Override
    public Product saveProduct(Product product) {
        System.out.println("Product being saved: " + product);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String id, Product newProduct) throws Exception {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isEmpty()) {
            throw new Exception("Product with id " + id + " does not exist");
        }
        Product product = existingProduct.get();
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        Iterable<Product> iterable = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        iterable.forEach(productList::add);
        return productList;
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }
}
