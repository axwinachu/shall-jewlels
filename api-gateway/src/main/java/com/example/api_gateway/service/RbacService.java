package com.example.api_gateway.service;

import com.example.api_gateway.config.RbacConfig;
import io.netty.handler.codec.http.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RbacService {

    private final RbacConfig config;
    private final AntPathMatcher matcher = new AntPathMatcher();

    public boolean isAllowed(String role, String path, HttpMethod method) {

        List<RbacConfig.Rule> rules = config.getRoles().get(role);
        if (rules == null) {
            return false;
        }

        return rules.stream().anyMatch(rule ->
                matcher.match(rule.getPath(), path)
                        && rule.getMethods().stream()
                        .map(HttpMethod::valueOf)
                        .anyMatch(m -> m.equals(method))
        );
    }
}
