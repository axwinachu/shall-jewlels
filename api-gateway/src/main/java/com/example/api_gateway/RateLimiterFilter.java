package com.example.api_gateway;

import com.example.api_gateway.config.RateLimiterConfig;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RateLimiterFilter implements GlobalFilter {
    private final RateLimiterConfig rateLimiterConfig;
    private final Map<String,Bucket> cache=new ConcurrentHashMap<>();
    Bucket resolveBuket(String ip,String path){
        String key=ip+":"+path;
        return cache.computeIfAbsent(key,k->{
            if(path.startsWith("/auth")||path.startsWith("/login")||path.startsWith("/oauth2")){
                return rateLimiterConfig.userServiceBuket();
            }
            if (path.startsWith("/product")){
                return rateLimiterConfig.productServiceBuket();
            }
            if (path.startsWith("/cart")){
                return rateLimiterConfig.cartServiceBucket();
            }
            if(path.startsWith("/order")){
                return rateLimiterConfig.orderServiceBuket();
            }
            return rateLimiterConfig.productServiceBuket();
        });
    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip=exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        String path=exchange.getRequest().getURI().getPath();

        Bucket bucket=resolveBuket(ip,path);

        if(bucket.tryConsume(1)){
            return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return exchange.getResponse().setComplete();
    }
}
