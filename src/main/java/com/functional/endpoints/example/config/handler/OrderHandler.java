package com.functional.endpoints.example.config.handler;

import com.functional.endpoints.example.dto.OrderDto;
import com.functional.endpoints.example.exception.EntityNotFoundException;
import com.functional.endpoints.example.model.Order;
import com.functional.endpoints.example.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class OrderHandler {

  private final OrderService orderService;

  public Mono<ServerResponse> getAllForUser(ServerRequest request) {
    var userId = request.queryParam("userId").map(Long::valueOf).orElseThrow();
    return orderService.getAll(userId).as(flux -> ServerResponse.ok().body(flux, OrderDto.class));
  }

  public Mono<ServerResponse> getAll(ServerRequest request) {
    return orderService.getAll().as(flux -> ServerResponse.ok().body(flux, OrderDto.class));
  }

  public Mono<ServerResponse> getOne(ServerRequest request) {
    var id = Long.parseLong(request.pathVariable("id"));

    return orderService
        .getOne(id)
        .switchIfEmpty(Mono.error(new EntityNotFoundException(Order.class.getSimpleName(), id)))
        .flatMap(ServerResponse.ok()::bodyValue);
  }

  public Mono<ServerResponse> save(ServerRequest request) {
    return request
        .bodyToMono(OrderDto.class)
        .as(orderService::save)
        .flatMap(ServerResponse.ok()::bodyValue);
  }

  public Mono<ServerResponse> update(ServerRequest request) {
    var id = Long.parseLong(request.pathVariable("id"));

    return request
        .bodyToMono(OrderDto.class)
        .as(mono -> orderService.update(id, mono))
        .switchIfEmpty(Mono.error(new EntityNotFoundException(OrderDto.class.getSimpleName(), id)))
        .flatMap(ServerResponse.ok()::bodyValue);
  }

  public Mono<ServerResponse> delete(ServerRequest request) {
    var id = Long.parseLong(request.pathVariable("id"));
    return orderService.delete(id).then(ServerResponse.ok().build());
  }
}
