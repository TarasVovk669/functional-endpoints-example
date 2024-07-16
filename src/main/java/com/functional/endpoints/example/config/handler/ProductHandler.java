package com.functional.endpoints.example.config.handler;

import com.functional.endpoints.example.dto.ProductDto;
import com.functional.endpoints.example.exception.EntityNotFoundException;
import com.functional.endpoints.example.model.Product;
import com.functional.endpoints.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {

  private final ProductService productService;

  public Mono<ServerResponse> getAll() {
    return productService.getAll().as(flux -> ServerResponse.ok().body(flux, ProductDto.class));
  }

  public Mono<ServerResponse> getOne(ServerRequest request) {
    var id = Long.parseLong(request.pathVariable("id"));

    return productService
        .getOne(id)
        .switchIfEmpty(Mono.error(new EntityNotFoundException(Product.class.getSimpleName(), id)))
        .flatMap(ServerResponse.ok()::bodyValue);
  }

  public Mono<ServerResponse> save(ServerRequest request) {
    return request
        .bodyToMono(ProductDto.class)
        .as(productService::save)
        .flatMap(ServerResponse.ok()::bodyValue);
  }

  public Mono<ServerResponse> update(ServerRequest request) {
    var id = Long.parseLong(request.pathVariable("id"));

    return request
        .bodyToMono(ProductDto.class)
        .as(mono -> productService.update(id, mono))
        .switchIfEmpty(Mono.error(new EntityNotFoundException(Product.class.getSimpleName(), id)))
        .flatMap(ServerResponse.ok()::bodyValue);
  }

  public Mono<ServerResponse> delete(ServerRequest request) {
    var id = Long.parseLong(request.pathVariable("id"));
    return productService.delete(id)
            .then(ServerResponse.ok().build());
  }
}
