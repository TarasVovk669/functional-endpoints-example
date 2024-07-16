package com.functional.endpoints.example.config;

import com.functional.endpoints.example.config.filter.LoggingFilter;
import com.functional.endpoints.example.config.filter.SecurityFilter;
import com.functional.endpoints.example.config.handler.ExceptionHandler;
import com.functional.endpoints.example.config.handler.OrderHandler;
import com.functional.endpoints.example.config.handler.ProductHandler;
import com.functional.endpoints.example.exception.EntityNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouteConfiguration {

  @Bean
  public RouterFunction<ServerResponse> productRoute(
      ProductHandler productHandler, ExceptionHandler exceptionHandler) {
    return RouterFunctions.route()
        .GET("/api/products", request -> productHandler.getAll())
        .GET("/api/products/{id}", productHandler::getOne)
        .POST("/api/products", productHandler::save)
        .PUT("/api/products/{id}", productHandler::update)
        .DELETE("/api/products/{id}", productHandler::delete)
        .onError(EntityNotFoundException.class, exceptionHandler::handleException)
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> orderRoute(OrderHandler orderHandler) {
    return RouterFunctions.route()
        .GET("/api/orders", orderHandler::getAllForUser)
        .GET("/api/orders/{id}", orderHandler::getOne)
        .POST("/api/orders", orderHandler::save)
        .PUT("/api/orders/{id}", orderHandler::update)
        .DELETE("/api/orders/{id}", orderHandler::delete)
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> nestedRoute(
      ProductHandler productHandler,
      OrderHandler orderHandler,
      ExceptionHandler exceptionHandler,
      SecurityFilter securityFilter,
      LoggingFilter loggingFilter) {
    return RouterFunctions.route()
        .GET(
            "/products",
            RequestPredicates.accept(MediaType.APPLICATION_JSON),
            request -> productHandler.getAll())
        .GET(
            "/products/{id}",
            RequestPredicates.headers(
                headers ->
                    null != headers.firstHeader("x-api-key")
                        && headers.firstHeader("x-api-key").equals("qwerty123")),
            productHandler::getOne)
        .POST("/products", productHandler::save)
        .PUT("/products/{id}", productHandler::update)
        .DELETE("/products/{id}", productHandler::delete)
        .path("orders", () -> orderRouteNested(orderHandler, securityFilter))
        .onError(EntityNotFoundException.class, exceptionHandler::handleException)
        .filter(loggingFilter)
        .build();
  }

  private RouterFunction<ServerResponse> orderRouteNested(
      OrderHandler orderHandler, SecurityFilter securityFilter) {
    return RouterFunctions.route()
        .GET(isOperation("get-for-user"), orderHandler::getAllForUser)
        .GET(isOperation("get-for-all"), orderHandler::getAll)
        .GET("/{id}", orderHandler::getOne)
        .POST(orderHandler::save)
        .PUT("/{id}", orderHandler::update)
        .DELETE("/{id}", orderHandler::delete)
        .filter(securityFilter::adminRoleFilter)
        .build();
  }

  private RequestPredicate isOperation(String operation) {
    return RequestPredicates.headers(h -> operation.equals(h.firstHeader("operation")));
  }
}
