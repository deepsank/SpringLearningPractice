package com.SpringAI.outputparser.controllers;

import com.SpringAI.outputparser.data.Author;
import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final ChatClient chatClient;

    public BooksController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/author/{author}")
    public @Nullable Map<String, Object> getAuthorDetails(@PathVariable String author){
        String promptMessage = """
                Generate a list of links for the author {author} Include the author name as the key, 
                and any other social/portfolio links as list/object of values.
                If you don't have the answer, then just say "I don't know".
                {format}
                """;
        MapOutputConverter converter = new MapOutputConverter();
        PromptTemplate promptTemplate = new PromptTemplate(promptMessage);
        Prompt prompt = promptTemplate.create(Map.of("author", author, "format", converter.getFormat()));
        return converter.convert(chatClient.prompt(prompt)
//                .user(message)
                .call()
                .content());
    }

    @GetMapping("/author")
    public Author getBooksByAuthor(@RequestParam( value = "author",
            defaultValue = "Ken Kousen") String author){
        String promptMessage = """
                Generate a list of books written by the author {author}
                If you aren't positive that a book belongs to the author, please don't include it.
                {format}
                """;
        BeanOutputConverter<Author> converter = new BeanOutputConverter(Author.class);
        PromptTemplate promptTemplate = new PromptTemplate(promptMessage);
        Prompt prompt = promptTemplate.create(Map.of("author", author, "format", converter.getFormat()));
        return converter.convert(chatClient.prompt(prompt)
//                .user(message)
                .call()
                .content());
    }
}
