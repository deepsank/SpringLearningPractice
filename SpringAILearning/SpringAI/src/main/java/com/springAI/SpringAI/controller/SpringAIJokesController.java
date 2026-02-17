package com.springAI.SpringAI.controller;

import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringAIJokesController {
    private final ChatClient chatClient;

    public SpringAIJokesController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/dad-jokes")
    public @Nullable String fetchDadJokes(@RequestParam (value="message",
            defaultValue = "Tell me a dad joke") String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
