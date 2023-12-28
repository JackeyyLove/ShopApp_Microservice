package com.java.CloudGateway.utility;

import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface KeyResolver {
    Mono<String> resolve(ServerWebExchange exchange);

}
