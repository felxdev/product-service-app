package com.capitole.products.application.queries;

import static java.util.Comparator.comparing;

import com.capitole.products.domain.exceptions.RateNotFoundException;
import com.capitole.products.domain.model.Rate;
import com.capitole.products.domain.repositories.RateRepository;
import java.util.List;
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
            .collectList()
            .flatMap(this::getRateByMaxPriority)
            .switchIfEmpty(Mono.error(
                new RateNotFoundException(q.productId(), q.brandId(), q.applicationDate()))));
  }

  private Mono<Rate> getRateByMaxPriority(List<Rate> rates) {
    if (rates.isEmpty()) {
      return Mono.empty();
    }

    return rates.stream()
        .max(comparing(Rate::priority))
        .map(Mono::just)
        .orElse(Mono.empty());
  }
}
