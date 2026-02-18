package com.SpringAI.Prompts.controller;

import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimplePromptContrroller {
    private final ChatClient chatClient;

    public SimplePromptContrroller(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("")
    public @Nullable String simple(@RequestParam(value="message",
            defaultValue = "Tell me a dad joke") String message){
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
