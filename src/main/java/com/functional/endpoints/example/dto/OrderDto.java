package com.functional.endpoints.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

  private Long id;
  private ProductDto productDto;
  private UserDto user;
}
