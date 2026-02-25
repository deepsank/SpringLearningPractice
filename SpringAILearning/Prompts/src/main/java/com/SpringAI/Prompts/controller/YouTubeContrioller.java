package com.SpringAI.Prompts.controller;

import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/youtube")
public class YouTubeContrioller {
    private final ChatClient chatClient;

    public YouTubeContrioller(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Value("classpath:/prompts/youtube.st")
    private Resource ytPromptResource;

    @GetMapping("/popular")
    public @Nullable String findPopularYoutuberByGenre(@RequestParam(value="genre",
            defaultValue = "tech") String genre){
//        String message = """
//                List 15 of the most popular YouTuber by {genre} along with their current subscriber counts.
//                If you don't have the answer, then just say "I don't know"
//                """;
//        PromptTemplate promptTemplate = new PromptTemplate(message);
        PromptTemplate promptTemplate = new PromptTemplate(ytPromptResource);
        Prompt prompt = promptTemplate.create(Map.of("genre", genre));
        return chatClient.prompt(prompt)
//                .user(message)
                .call()
                .content();
    }
}
