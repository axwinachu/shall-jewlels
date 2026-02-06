package com.example.api_gateway.security;

import com.example.api_gateway.service.RbacService;
import io.jsonwebtoken.Claims;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthGatewayFilterFactory
        extends AbstractGatewayFilterFactory<JwtAuthGatewayFilterFactory.Config> {

    private final JwtUtil jwtUtil;
    private final RbacService rbacService;

    public JwtAuthGatewayFilterFactory(JwtUtil jwtUtil, RbacService rbacService) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
        this.rbacService = rbacService;
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            if (path.startsWith("/auth")) {
                return chain.filter(exchange);

            }

            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return onError(exchange, "Missing or invalid Authorization header",
                        HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);

            try {
                Claims claims = jwtUtil.validateToken(token);

                String userId = claims.get("userId").toString();
                String role   = claims.get("role").toString();

                if (request.getMethod() == null) {
                    return onError(exchange, "Invalid HTTP method",
                            HttpStatus.METHOD_NOT_ALLOWED);
                }

                HttpMethod nettyMethod =
                        HttpMethod.valueOf(request.getMethod().name());


                if (!rbacService.isAllowed(role, path, nettyMethod)) {
                    return onError(exchange, "Access Denied",
                            HttpStatus.FORBIDDEN);
                }


                ServerHttpRequest modifiedRequest = request.mutate()
                        .header("X-USER-ID", userId)
                        .header("X-USER-ROLE", role)
                        .build();

                return chain.filter(
                        exchange.mutate().request(modifiedRequest).build()
                );

            } catch (Exception e) {
                return onError(exchange, "Invalid or expired token",
                        HttpStatus.UNAUTHORIZED);
            }
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange,
                               String err,
                               HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }

    public static class Config {

    }
}
