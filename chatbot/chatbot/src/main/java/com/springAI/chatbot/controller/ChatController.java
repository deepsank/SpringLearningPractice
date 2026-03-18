package com.springAI.chatbot.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private ChatClient chatClient;
    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping("/chat")
    public String chat(@RequestParam(value = "message") String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/stream")
    public Flux<String> chatWithStream(@RequestParam(value = "message") String message){
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }
}
