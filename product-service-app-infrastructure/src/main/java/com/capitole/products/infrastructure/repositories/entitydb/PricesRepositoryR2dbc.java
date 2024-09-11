package com.capitole.products.infrastructure.repositories.entitydb;

import java.time.LocalDateTime;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PricesRepositoryR2dbc extends ReactiveCrudRepository<PricesEntity, PricesId> {

  Flux<PricesEntity> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
      String productId, String brandId, LocalDateTime date);

}
