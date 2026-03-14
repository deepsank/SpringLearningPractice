package com.springAI.SpringDocumentation.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.io.Resource;
import org.springframework.shell.command.annotation.Command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Command
public class SpringAssistantCommand {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/prompts/spring-boot-reference.st")
    private Resource sbPromptTemplate;

    public  SpringAssistantCommand(ChatClient chatClient, VectorStore vectorStore){
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }

    @Command(command = "q")
    public String question(@DefaultValue(value = "What is Spring Boot?") String message){
        PromptTemplate promptTemplate = new PromptTemplate(sbPromptTemplate);
        Map<String, Object> promptParams = new HashMap<>();
        promptParams.put("input",message);
        promptParams.put("documents", String.join("\n", findSimilarDocuments(message)));
        Prompt prompt = promptTemplate.create(promptParams);

        return chatClient.prompt(prompt)
                .call()
                .content();
    }

    private List<String> findSimilarDocuments(String message) {
        List<Document> similarDocuments =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query(message)
                                .topK(1)
                                .build());

        return similarDocuments.stream()
                .map(doc -> {
                    String content = doc.getFormattedContent();
                    return content.length() > 1500
                            ? content.substring(0, 1500)
                            : content;
                })
                .toList();
    }
}
