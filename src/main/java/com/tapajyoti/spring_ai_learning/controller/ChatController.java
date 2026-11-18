package com.tapajyoti.spring_ai_learning.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    // Spring AI automatically provides and injects ChatClient.Builder
    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    /**
     * Simple GET endpoint to test Gemini API.
     * Example: http://localhost:8080/chat?prompt=hello
     */
    @GetMapping("/chat")
    public String chat(@RequestParam(value = "prompt", defaultValue = "Tell me a fun fact about programming") String prompt) {
        try {
            return chatClient.prompt()
                    .user(prompt)
                    .call()
                    .content();
        } catch (Exception e) {
            String cause = e.getCause() != null ? " | Cause: " + e.getCause().getMessage() : "";
            return "Error calling Gemini API: " + e.getMessage() + cause;
        }
    }
}
