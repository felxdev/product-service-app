package com.capitole.products.domain.repositories;

import com.capitole.products.domain.model.Rate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RateRepository {

  Flux<Rate> findByProductIdAndBrandIdAndDate(Integer productId, Integer brandId, String date);

}
