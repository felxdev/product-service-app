package com.capitole.products.infrastructure.repositories.rate;

import com.capitole.products.domain.model.Rate;
import com.capitole.products.domain.repositories.RateRepository;
import com.capitole.products.infrastructure.repositories.rate.postgres.PricesRepositoryR2dbc;
import com.capitole.products.infrastructure.repositories.rate.postgres.PricesRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class RateRepositoryImpl implements RateRepository {

  private final PricesRepositoryR2dbc pricesRepositoryR2dbc;

  private final PricesRowMapper pricesRowMapper;

  @Override
  public Flux<Rate> findByProductIdAndBrandIdAndDate(Integer productId, Integer brandId,
      String date) {
    return Flux.just(productId)
        .map(pId -> pricesRowMapper.asFindRatesByFilter(pId, brandId, date))
        .flatMap(filter -> pricesRepositoryR2dbc
            .findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                filter.productId(), filter.brandId(), filter.date(), filter.date()))
        .map(pricesRowMapper::asRate);
  }
}
