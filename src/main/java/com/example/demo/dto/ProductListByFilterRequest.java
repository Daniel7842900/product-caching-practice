package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductListByFilterRequest {
    @NotBlank
    private String category;

    @Min(0)
    private int page;

    @Min(1)
    private int size;
}
