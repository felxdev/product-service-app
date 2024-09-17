package com.capitole.products.domain.model;

import lombok.Builder;

@Builder
public record Rate(
    String id,
    Integer productId,
    Integer brandId,
    String startDate,
    String endDate,
    Integer appliedRate,
    String priority,
    Amount price
) {

}
