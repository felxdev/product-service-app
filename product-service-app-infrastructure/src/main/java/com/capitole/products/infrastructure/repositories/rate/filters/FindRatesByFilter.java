package com.capitole.products.infrastructure.repositories.rate.filters;

import lombok.Builder;

@Builder
public record FindRatesByFilter(
    Integer productId,
    Integer brandId,
    String date) {

}
