package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductCreateRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
public class ProductService {
    private ProductCacheService productCacheService;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductCacheService productCacheService,  ProductRepository productRepository) {
        this.productCacheService = productCacheService;
        this.productRepository = productRepository;
    }

    public ProductResponse findProduct(String id) {
        return new ProductResponse(UUID.randomUUID(), "test", "testCat", new BigDecimal(0.11), Instant.now());
    }

    @Transactional
    public ProductResponse createProduct(ProductCreateRequest productCreateRequest) {
        Product product = new Product(
                UUID.randomUUID(),
                productCreateRequest.name(),
                productCreateRequest.category(),
                productCreateRequest.price()
        );

        Product saved = productRepository.save(product);

        return toResponse(saved);
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getUpdatedAt()
        );
    }
}
