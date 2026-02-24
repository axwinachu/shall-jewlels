package com.example.email_service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @KafkaListener(topics = "my-order-3", containerFactory = "email-kafka-listener")
    public void consume(Map<String, Object> map, Acknowledgment ack) {

        try {
            String email = String.valueOf(map.get("email"));
            String name = String.valueOf(map.get("name"));
            String address = String.valueOf(map.get("address"));
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("aswindev2465@gmail.com");
            message.setTo(email);
            message.setSubject("Shaaa Jewels");
            message.setText(name + ", your order placed successfully. Address: " + address);
            mailSender.send(message);
            System.out.println("Email sent successfully");
            ack.acknowledge();
        } catch (Exception ex) {
            throw new RuntimeException("Email sending failed", ex);
        }
    }
}