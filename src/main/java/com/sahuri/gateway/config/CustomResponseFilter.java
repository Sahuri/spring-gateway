package com.sahuri.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CustomResponseFilter extends AbstractGatewayFilterFactory<CustomResponseFilter.Config> {

    public static class Config {
        // Configurations if needed
    }

    public CustomResponseFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            return handleCustomResponse(exchange).then(chain.filter(exchange));
        };
    }

    private Mono<Void> handleCustomResponse(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        String responseBody = "{\"message\":\"This is a custom response\"}";
        byte[] bytes = responseBody.getBytes();
        var buffer = exchange.getResponse().bufferFactory().wrap(bytes);

        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}

