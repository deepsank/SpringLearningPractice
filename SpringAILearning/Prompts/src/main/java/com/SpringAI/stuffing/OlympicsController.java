package com.SpringAI.stuffing;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/olympics")
public class OlympicsController {
    private ChatClient chatClient;
    public OlympicsController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
    Resource olympicSportsResource = new ClassPathResource("/prompts/olympic-sports.st");
//        @Value("classpath:/prompts/olympic-sports.st")
//        private Resource olympicSportsResource;
    Resource docsToStuffResource = new ClassPathResource("/docs/olympic-sports.txt");
    @GetMapping("/2028")
    public String fetch2028OlympicSport(
            @RequestParam(value = "message", defaultValue = "What sports are being included in the 2028 summer olympics?") String message,
            @RequestParam(value = "stuffit", defaultValue = "false") boolean stuffit
    ){  PromptTemplate promptTemplate = new PromptTemplate(olympicSportsResource);
        Map<String,Object> map = new HashMap<>();
        map.put("question",message);
        if(stuffit){
            map.put("context",docsToStuffResource);
        }
        else {
            map.put("context","");
        }
        Prompt prompt = promptTemplate.create(map);
        return chatClient.prompt(prompt)
//                .user(message)
                .call()
                .content();
    }
}
