package com.functional.endpoints.example.service;

import com.functional.endpoints.example.dto.OrderDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Flux<OrderDto> getAll(Long userId);
    Flux<OrderDto> getAll();

    Mono<OrderDto> getOne(Long id);

    Mono<OrderDto> save(Mono<OrderDto> productDtoMono);

    Mono<OrderDto> update(Long id, Mono<OrderDto> productDtoMono);

    Mono<Void> delete(Long id);

}
