package com.SpringAI.Prompts.controller;


import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DadJokesController {
    private final ChatClient chatClient;

    public DadJokesController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/dad-jokes")
    public @Nullable String dadJokes(){
        var system = new SystemMessage("Your primary function is to tell dad " +
                "jokes. If someone asks you for any other type of jokes," +
                " please tell them only dad jokes related to the topic asked.");
        var user = new UserMessage("Tell me a serious joke about universe.");
        Prompt prompt = new Prompt(system, user);
        return chatClient.prompt(prompt)
//                .user(message)
                .call()
                .content();
    }
}
