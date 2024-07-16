package com.functional.endpoints.example.config.filter;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SecurityFilter {

    public Mono<ServerResponse> adminRoleFilter(ServerRequest request, HandlerFunction<ServerResponse> next) {
        return SecurityFilter.requireRole("ADMIN", request.exchange())
                .flatMap(exchange -> next.handle(request))
                .onErrorResume(SecurityException.class, ex -> ServerResponse.status(HttpStatus.FORBIDDEN).build());
    }

    private static Mono<ServerWebExchange> requireRole(String role, ServerWebExchange exchange) {
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(securityContext -> {
                    Authentication authentication = securityContext.getAuthentication();
                    if (authentication != null && authentication.isAuthenticated()) {
                        Object principal = authentication.getPrincipal();
                        if (principal instanceof UserDetails userDetails) {
                            if (userDetails.getAuthorities().stream()
                                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + role))) {
                                return Mono.just(exchange);
                            }
                        }
                    }
                    return Mono.error(new SecurityException("Access Denied"));
                });
    }
}
