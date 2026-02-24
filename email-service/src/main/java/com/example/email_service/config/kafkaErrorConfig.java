package com.example.email_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class kafkaErrorConfig {
    @Bean
    DefaultErrorHandler errorHandler(KafkaTemplate<Object,Object> template){
        DeadLetterPublishingRecoverer recoverer=new DeadLetterPublishingRecoverer(template);
        FixedBackOff fixedBackOff=new FixedBackOff(3000L,3);
        return new DefaultErrorHandler(recoverer,fixedBackOff);
    }
}
