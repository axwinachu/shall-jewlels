package com.example.email_service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    @KafkaListener(topics ="my-order-3",groupId = "email-1")
    @Async
    public String getMessage(HashMap<String,Object> map){
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("aswindev2465@gmail.com");
                message.setTo(map.get("email").toString());
                message.setSubject("shaa jewels");
                message.setText(map.get("name")+"your order has been placed successfully address"+map.get("address"));
                javaMailSender.send(message);
            }
            catch (Exception ex){
                throw  new RuntimeException(ex.getMessage());
            }
      return "mail send successfully";
    }
}
