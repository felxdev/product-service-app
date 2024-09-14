package com.capitole.products.infrastructure.repositories.entitydb;

import java.time.LocalDateTime;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PricesRepositoryR2dbc extends R2dbcRepository<PricesEntity, PricesId> {

  Flux<PricesEntity> findByProductIdAndBrandIdAndStartDateAfterAndEndDateBefore(
      String productId, String brandId, LocalDateTime dateAfter, LocalDateTime dateBefore);

}
