package com.capitole.products.application.queries;

import com.capitole.products.domain.exceptions.RateNotFoundException;
import com.capitole.products.domain.model.Rate;
import com.capitole.products.domain.repositories.RateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindRateByProductIdAndApplicationDateAndBrandIdQueryHandler implements
    QueryHandler<FindRateByProductIdAndApplicationDateAndBrandIdQuery, Mono<Rate>> {

  private final RateRepository rateRepository;

  @Override
  public Mono<Rate> execute(FindRateByProductIdAndApplicationDateAndBrandIdQuery query) {
    return Mono.just(query)
        .doOnNext(q -> log.debug("Finding rate with: {}", q))
        .flatMap(q -> rateRepository.findByProductIdAndBrandIdAndDate(
                q.productId(), q.brandId(), q.applicationDate())
            .switchIfEmpty(Mono.error(
                new RateNotFoundException(q.productId(), q.brandId(), q.applicationDate()))));
  }
}
