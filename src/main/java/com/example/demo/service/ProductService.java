package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.dto.ProductCreateRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.dto.ProductUpdateRequest;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public ProductResponse findProduct(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));

        return toResponse(product);
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

    @Transactional
    public ProductResponse updateProduct(UUID id, ProductUpdateRequest productUpdateRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));

        product.setName(productUpdateRequest.getName());
        product.setCategory(productUpdateRequest.getCategory());
        product.setPrice(productUpdateRequest.getPrice());

        productRepository.save(product);

        return toResponse(product);
    }

    @Transactional
    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));

        productRepository.delete(product);
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
