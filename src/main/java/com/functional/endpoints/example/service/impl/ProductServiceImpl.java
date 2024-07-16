package com.functional.endpoints.example.service.impl;

import com.functional.endpoints.example.dto.ProductDto;
import com.functional.endpoints.example.mapper.EntityDtoMapper;
import com.functional.endpoints.example.repository.ProductRepository;
import com.functional.endpoints.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  @Override
  public Flux<ProductDto> getAll() {
    return repository.findAll().map(EntityDtoMapper::toDto);
  }

  @Override
  public Mono<ProductDto> getOne(Long id) {
    return repository.findById(id)
            .map(EntityDtoMapper::toDto);
  }

  @Override
  public Mono<ProductDto> save(Mono<ProductDto> productDtoMono) {
    return productDtoMono
        .map(EntityDtoMapper::toEntity)
        .flatMap(repository::save)
        .map(EntityDtoMapper::toDto);
  }

  @Override
  public Mono<ProductDto> update(Long id, Mono<ProductDto> productDtoMono) {
    return repository
        .findById(id)
        .flatMap(entity -> productDtoMono)
        .doOnNext(c -> c.setId(id))
        .map(EntityDtoMapper::toEntity)
        .flatMap(repository::save)
        .map(EntityDtoMapper::toDto);
  }

  @Override
  public Mono<Void> delete(Long id) {
    return repository.deleteById(id);
  }
}
