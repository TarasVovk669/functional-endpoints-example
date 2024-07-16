package com.functional.endpoints.example.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

  private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

  @Override
  public Mono<ServerResponse> filter(ServerRequest request, HandlerFunction<ServerResponse> next) {
    var start = System.currentTimeMillis();
    return next.handle(request)
        .doOnEach(
            signal -> {
              if (!signal.isOnComplete()) {
                var executionTime = System.currentTimeMillis() - start;
                log.info(
                    "Request: {} {} executed in {} ms",
                    request.method(),
                    request.path(),
                    executionTime);
              }
            });
  }
}
