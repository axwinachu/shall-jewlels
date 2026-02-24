package com.example.email_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;

import java.util.Map;

@Configuration
public class KafkaListenerConfig {
    @Bean(name = "email-kafka-listener")
    ConcurrentKafkaListenerContainerFactory<String,Map<String,Object>> kafkaListenerFactory(ConsumerFactory<String,Map<String,Object>> consumerFactory, DefaultErrorHandler errorHandler){
        ConcurrentKafkaListenerContainerFactory<String,Map<String,Object>> factory=new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);

        //parallel consumers
        factory.setConcurrency(3);

        //manual offset commit
        factory.getContainerProperties()
                .setAckMode(ContainerProperties.AckMode.MANUAL);

        // retry + DLQ
        factory.setCommonErrorHandler(errorHandler);

        factory.getContainerProperties().setPollTimeout(3000);

        return factory;
    }
}
