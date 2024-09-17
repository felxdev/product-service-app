package com.capitole.products.infrastructure.repositories.rate.postgres;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PricesRepositoryR2dbc extends R2dbcRepository<PricesRow, Long> {

  Flux<PricesRow> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
      Integer productId, Integer brandId, String lessDate, String GreaterDate);
}
