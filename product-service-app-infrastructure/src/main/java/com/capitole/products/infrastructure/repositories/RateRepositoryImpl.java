package com.capitole.products.infrastructure.repositories;

import com.capitole.products.domain.model.Rate;
import com.capitole.products.domain.repositories.RateRepository;
import com.capitole.products.infrastructure.repositories.entitydb.PricesEntityMapper;
import com.capitole.products.infrastructure.repositories.entitydb.PricesRepositoryR2dbc;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Repository
public class RateRepositoryImpl implements RateRepository {

  private final PricesRepositoryR2dbc pricesRepositoryR2Dbc;

  private final PricesEntityMapper rateEntityMapper;

  @Override
  public Flux<Rate> findByProductIdAndBrandIdAndDate(String productId, String brandId,
      LocalDate date) {
    return pricesRepositoryR2Dbc.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            productId, brandId, date)
        .map(rateEntityMapper::asRate);
  }
}
