package com.example.demo.dto;

import java.util.List;

public record ProductPageResponse(
        List<ProductResponse> products,
        int page,
        int size,
        long total
) {}
