package com.capitole.products.infrastructure.repositories;

import com.capitole.products.domain.model.Rate;
import com.capitole.products.domain.repositories.RateRepository;
import com.capitole.products.infrastructure.repositories.entitydb.PriceEntityMapper;
import com.capitole.products.infrastructure.repositories.entitydb.PriceRepositoryR2dbc;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Repository
public class RateRepositoryImpl implements RateRepository {

  private final PriceRepositoryR2dbc priceRepositoryR2dbc;

  private final PriceEntityMapper rateEntityMapper;

  @Override
  public Flux<Rate> findByProductIdAndBrandIdAndDate(String productId, String brandId,
      LocalDate date) {
    return priceRepositoryR2dbc.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            productId, brandId, date)
        .map(rateEntityMapper::asRate);
  }
}
