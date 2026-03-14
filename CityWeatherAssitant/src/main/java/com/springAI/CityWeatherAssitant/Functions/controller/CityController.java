package com.springAI.CityWeatherAssitant.Functions.controller;

import com.springAI.CityWeatherAssitant.Functions.tools.WeatherTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {

    private final ChatClient chatClient;
    private final WeatherTools weatherTools;



    public CityController(ChatClient chatClient, WeatherTools weatherTools) {
        this.chatClient = chatClient;
        this.weatherTools = weatherTools;
    }

    @GetMapping("/cities")
    public String cities(@RequestParam(value = "message") String message){
        SystemMessage systemMessage = new SystemMessage("You are a helpful AI assistant answering questions about cities around the world.");
        UserMessage userMessage = new UserMessage(message);
        Prompt prompt = new Prompt(systemMessage, userMessage);
        return chatClient.prompt(prompt)
                .tools(weatherTools)
                //                .user(message)
                .call()
                .content();


    }
}
