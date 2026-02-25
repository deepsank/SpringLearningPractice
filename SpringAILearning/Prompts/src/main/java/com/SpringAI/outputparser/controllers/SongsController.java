package com.SpringAI.outputparser.controllers;

import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SongsController {
    private final ChatClient chatClient;

    public SongsController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/songs")
    public @Nullable List<String> getSongsByArtist(@RequestParam(value="artist",
            defaultValue = "Taylor Swift") String artist){
        String promptMessage = """
                Please give me a list 15 of the most popular songs by the artist {artist}.
                If you don't have the answer, then just say "I don't know".
                {format}
                """;
        ListOutputConverter converter = new ListOutputConverter();
        PromptTemplate promptTemplate = new PromptTemplate(promptMessage);
        Prompt prompt = promptTemplate.create(Map.of("artist", artist, "format", converter.getFormat()));
        return converter.convert(chatClient.prompt(prompt)
//                .user(message)
                .call()
                .content());
    }
}
