package com.capitole.products.infrastructure.repositories.entitydb;

import java.time.LocalDate;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PriceRepositoryR2dbc extends ReactiveCrudRepository<PricesEntity, PricesId> {

  Flux<PricesEntity> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
      String productId, String brandId, LocalDate date);

}
