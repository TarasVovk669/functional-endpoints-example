package com.functional.endpoints.example.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {

  private static final String MESSAGE = "%s with id=%d is not found";

  private final String entityName;

  public EntityNotFoundException(String entityName, Long id) {
    super(MESSAGE.formatted(entityName, id));
    this.entityName = entityName;
  }
}
