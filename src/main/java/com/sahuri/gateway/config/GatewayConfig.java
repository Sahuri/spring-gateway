package com.sahuri.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("custom_filter_route", r -> r.path("/access/token").and().method(HttpMethod.POST)
                        .filters(f -> f.filter(new CustomResponseFilter().apply(new CustomResponseFilter.Config())))
                        .uri("no://op"))
                .build();
    }
}
