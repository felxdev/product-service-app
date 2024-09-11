package com.capitole.products.application.queries;

import jakarta.validation.Valid;
import reactor.core.CorePublisher;

public interface QueryHandler<T extends Query, R extends CorePublisher<?>> {

  R execute(@Valid T query);

}
