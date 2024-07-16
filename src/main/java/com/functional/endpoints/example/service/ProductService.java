package com.functional.endpoints.example.service;

import com.functional.endpoints.example.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<ProductDto> getAll();

    Mono<ProductDto> getOne(Long id);

    Mono<ProductDto> save(Mono<ProductDto> productDtoMono);

    Mono<ProductDto> update(Long id, Mono<ProductDto> productDtoMono);

    Mono<Void> delete(Long id);

}
