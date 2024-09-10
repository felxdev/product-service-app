package com.capitole.products.domain.model;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record Rate(
    String productId,
    LocalDate startDate,
    LocalDate endDate,
    Integer appliedRate,
    Amount price
) {

}
