package com.functional.endpoints.example.config.handler;

import com.functional.endpoints.example.exception.EntityNotFoundException;
import java.net.URI;
import java.util.function.Consumer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ExceptionHandler {

  public Mono<ServerResponse> handleException(EntityNotFoundException ex, ServerRequest request) {
    return handleException(
        HttpStatus.NOT_FOUND,
        ex,
        request,
        problem -> problem.setTitle(ex.getEntityName() + " Not Found"));
  }

  private Mono<ServerResponse> handleException(
      HttpStatus status, Exception ex, ServerRequest request, Consumer<ProblemDetail> consumer) {
    var problem = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
    problem.setInstance(URI.create(request.path()));
    consumer.accept(problem);
    return ServerResponse.status(status).bodyValue(problem);
  }
}
