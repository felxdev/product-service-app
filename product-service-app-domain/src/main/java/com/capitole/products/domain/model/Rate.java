package com.capitole.products.domain.model;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record Rate(
    String productId,
    String brandId,
    LocalDate startDate,
    LocalDate endDate,
    Integer appliedRate,
    String priority,
    Amount price
) {

}
