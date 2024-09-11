package com.capitole.products.domain.repositories;

import com.capitole.products.domain.model.Rate;
import java.time.LocalDate;
import reactor.core.publisher.Flux;

public interface RateRepository {

  Flux<Rate> findByProductIdAndBrandIdAndDate(String productId, String brandId, LocalDate date);
}
