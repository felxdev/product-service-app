package com.capitole.products.domain.model;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record Rate(
    String productId,
    String brandId,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer appliedRate,
    String priority,
    Amount price
) {

}
