package com.capitole.products.api.rest.controllers;

import com.capitole.products.api.rest.dtos.RateInfoDto;
import com.capitole.products.application.queries.FindRateByProductIdAndApplicationDateAndBrandIdQueryHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*",
    methods = {
        RequestMethod.GET
    })
public class RatesApiController implements RatesApi {

  private final RateDtoMapper mapper;

  private final FindRateByProductIdAndApplicationDateAndBrandIdQueryHandler queryHandler;

  @Override
  public Mono<ResponseEntity<RateInfoDto>> getRateByProductIdAndBrandIdAndDate(
      String applicationDate, Integer productId, Integer brandId,
      ServerWebExchange exchange) {

    return Mono.just(productId)
        .map(pId -> mapper.asFindRateByProductIdAndApplicationDateAndBrandIdQuery(pId,
            applicationDate, brandId))
        .flatMap(queryHandler::execute)
        .map(mapper::asRateInfoDto)
        .map(ResponseEntity::ok);
  }
}
