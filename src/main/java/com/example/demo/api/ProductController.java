package com.example.demo.api;


import com.example.demo.dto.*;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable UUID id) {
        ProductResponse productResponse = productService.findProduct(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProductPageResponse> getProductsByFilters(@Valid ProductListByFilterRequest productListByFilterRequest) {
        ProductPageResponse productPageResponse = productService.findProductsByCategory(productListByFilterRequest);
        return new ResponseEntity<>(productPageResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        ProductResponse productResponse = productService.createProduct(productCreateRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable UUID id, @RequestBody ProductUpdateRequest productUpdateRequest) {
        ProductResponse productResponse = productService.updateProduct(id, productUpdateRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
