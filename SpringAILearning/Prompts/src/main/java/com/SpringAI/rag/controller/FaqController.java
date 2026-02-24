package com.SpringAI.rag.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FaqController {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    @Value("classpath:/prompts/rag-prompt-template.st")
    private Resource ragPromptResource;

    FaqController(ChatClient chatClient, VectorStore vectorStore) {
        this.chatClient = chatClient;
        this.vectorStore = vectorStore;
    }
    @GetMapping("/faq")
    public String faq(@RequestParam(value = "message", defaultValue = "How can I but tickets for the LA Olympics 2028?") String message) {
        List<Document> similarDocuments = vectorStore.similaritySearch(SearchRequest.builder().query(message).topK(2).build());
        List<String> contentList = similarDocuments.stream().map(Document::getFormattedContent).toList();
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptResource);
        Map<String, Object> params = new HashMap<>();
        params.put("input", message);
        params.put("documents", String.join("\n", contentList));
        Prompt prompt = promptTemplate.create(params);
        return chatClient.prompt(prompt)
                .call()
                .content();
    }
}
