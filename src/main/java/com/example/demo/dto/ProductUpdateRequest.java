package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductUpdateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotBlank
    @Positive
    private BigDecimal price;
}
