package com.capitole.products.domain.repositories;

import com.capitole.products.domain.model.Rate;
import reactor.core.publisher.Mono;

public interface RateRepository {

  Mono<Rate> findByProductIdAndBrandIdAndDate(String productId, String brandId, String date);
}
