package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.dto.*;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repo.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional(readOnly = true)
    public ProductPageResponse findProductsByCategory(@Valid ProductListByFilterRequest productListByFilterRequest) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updatedAt").and(Sort.by(Sort.Direction.ASC, "id"));

        PageRequest pageRequest = PageRequest.of(productListByFilterRequest.getPage(), productListByFilterRequest.getSize(), sort);

        Page<Product> result = productRepository.findByCategory(productListByFilterRequest.getCategory(), pageRequest);

        List<ProductResponse> items = result.getContent()
                .stream()
                .map(this::toResponse)
                .toList();

        return new ProductPageResponse(items, result.getNumber(), result.getSize(), result.getTotalElements());
    }
}
