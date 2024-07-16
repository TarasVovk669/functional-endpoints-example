package com.functional.endpoints.example.mapper;

import com.functional.endpoints.example.dto.OrderDto;
import com.functional.endpoints.example.dto.ProductDto;
import com.functional.endpoints.example.dto.UserDto;
import com.functional.endpoints.example.model.Order;
import com.functional.endpoints.example.model.Product;

public class EntityDtoMapper {

  public static Product toEntity(ProductDto dto) {
    var product = new Product();
    product.setId(dto.getId());
    product.setName(dto.getName());
    product.setPrice(dto.getPrice());
    return product;
  }

  public static ProductDto toDto(Product product) {
    return ProductDto.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .build();
  }

  public static Order toEntity(OrderDto dto) {
    var order = new Order();
    order.setId(dto.getId());
    order.setProductId(dto.getProductDto().getId());
    order.setUserId(dto.getUser().getId());
    return order;
  }

  public static OrderDto toDto(Order order, Product product) {
    return OrderDto.builder()
        .id(order.getId())
        .productDto(toDto(product))
        .user(UserDto.builder().id(order.getUserId()).build())
        .build();
  }
}
