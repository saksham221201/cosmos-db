package com.saksham.cosmos.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.saksham.cosmos.entity.Product;

public interface ProductRepository extends CosmosRepository<Product, String> {
}
