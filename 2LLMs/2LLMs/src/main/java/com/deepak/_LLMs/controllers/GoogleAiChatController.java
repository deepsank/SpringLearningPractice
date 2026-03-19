package com.deepak._LLMs.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleAiChatController {

    private ChatClient chatClient;
    public GoogleAiChatController(@Qualifier("googleAiChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/gemini")
    public String gemini(){
        return chatClient.prompt()
                .user("Tell me an interesting fact about Anthropic.")
                .call()
                .content();
    }
}
