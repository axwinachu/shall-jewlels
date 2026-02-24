package com.example.api_gateway.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RateLimiterConfig {

    public Bucket userServiceBuket(){
        return Bucket.builder()
                .addLimit(Bandwidth
                        .simple(5, Duration.ofMinutes(1))).build();
    }
    public Bucket productServiceBuket(){
        return Bucket.builder()
                .addLimit(Bandwidth
                        .simple(20,Duration.ofMinutes(1))).build();
    }
    public Bucket cartServiceBucket(){
        return Bucket.builder()
                .addLimit(Bandwidth.simple(20,Duration.ofMinutes(1))).build();
    }
    public Bucket orderServiceBuket(){
        return Bucket.builder()
                .addLimit(Bandwidth.simple(20,Duration.ofMinutes(1))).build();
    }
}
