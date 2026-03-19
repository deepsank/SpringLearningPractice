package com.deepak._LLMs.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiChatController {

    private ChatClient chatClient;
    public OpenAiChatController(@Qualifier("openAiChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/openAI")
    public String openAI(){
        return chatClient.prompt()
                .user("Tell me an interesting fact about ChatGPT.")
                .call()
                .content();
    }
}
