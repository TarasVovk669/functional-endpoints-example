package com.functional.endpoints.example.service.impl;

import com.functional.endpoints.example.dto.OrderDto;
import com.functional.endpoints.example.mapper.EntityDtoMapper;
import com.functional.endpoints.example.model.Order;
import com.functional.endpoints.example.model.Product;
import com.functional.endpoints.example.repository.OrderRepository;
import com.functional.endpoints.example.repository.ProductRepository;
import com.functional.endpoints.example.service.OrderService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository repository;
  private final ProductRepository productRepository;

  @Override
  public Flux<OrderDto> getAll(Long userId) {
    return repository
        .findAllByUserId(userId)
        .collectList()
        .flatMapMany(
            orders -> {
              List<Long> productIds =
                  orders.stream().map(Order::getProductId).distinct().collect(Collectors.toList());
              return productRepository
                  .findAllById(productIds)
                  .collectMap(Product::getId)
                  .flatMapMany(
                      productMap ->
                          Flux.fromIterable(orders)
                              .map(
                                  order ->
                                      EntityDtoMapper.toDto(
                                          order, productMap.get(order.getProductId()))));
            });
  }

  @Override
  public Flux<OrderDto> getAll() {
    return repository
        .findAll()
        .collectList()
        .flatMapMany(
            orders -> {
              List<Long> productIds =
                  orders.stream().map(Order::getProductId).distinct().collect(Collectors.toList());
              return productRepository
                  .findAllById(productIds)
                  .collectMap(Product::getId)
                  .flatMapMany(
                      productMap ->
                          Flux.fromIterable(orders)
                              .map(
                                  order ->
                                      EntityDtoMapper.toDto(
                                          order, productMap.get(order.getProductId()))));
            });
  }

  @Override
  public Mono<OrderDto> getOne(Long id) {
    return repository
        .findById(id)
        .zipWhen(order -> productRepository.findById(order.getProductId()))
        .map(tuple -> EntityDtoMapper.toDto(tuple.getT1(), tuple.getT2()));
  }

  @Override
  public Mono<OrderDto> save(Mono<OrderDto> OrderDtoMono) {
    return OrderDtoMono.map(EntityDtoMapper::toEntity)
        .flatMap(repository::save)
        .zipWhen(order -> productRepository.findById(order.getProductId()))
        .map(tuple -> EntityDtoMapper.toDto(tuple.getT1(), tuple.getT2()));
  }

  @Override
  public Mono<OrderDto> update(Long id, Mono<OrderDto> orderDtoMono) {
    return repository
        .findById(id)
        .flatMap(entity -> orderDtoMono)
        .doOnNext(c -> c.setId(id))
        .map(EntityDtoMapper::toEntity)
        .flatMap(repository::save)
        .zipWhen(order -> productRepository.findById(order.getProductId()))
        .map(tuple -> EntityDtoMapper.toDto(tuple.getT1(), tuple.getT2()));
  }

  @Override
  public Mono<Void> delete(Long id) {
    return repository.deleteById(id);
  }
}
