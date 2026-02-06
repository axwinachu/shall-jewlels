package com.example.api_gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

@ConfigurationProperties(prefix = "security")
@Component
@Data
public class RbacConfig {

    private Map<String, List<Rule>> roles = new HashMap<>();

    @Data
    public static class Rule {
        private String path;
        private List<String> methods; // ← IMPORTANT
    }
}
