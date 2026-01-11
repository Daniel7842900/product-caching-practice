package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ProductResponse(UUID id, String name, String category, BigDecimal price, Instant updatedAt) {}
