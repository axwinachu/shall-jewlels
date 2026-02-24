package com.example.user_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final ChatClient chatClient;

    public String askAI(String question) {

        return chatClient.prompt()
                .system("only replay to jewels product and suggestion to buy")
                .user(question)
                .call()
                .content();
    }
}