package com.capitole.products.domain.repositories;

import com.capitole.products.domain.model.Rate;
import reactor.core.publisher.Flux;

public interface RateRepository {

  Flux<Rate> findByProductIdAndBrandIdAndDate(Integer productId, Integer brandId, String date);

}
