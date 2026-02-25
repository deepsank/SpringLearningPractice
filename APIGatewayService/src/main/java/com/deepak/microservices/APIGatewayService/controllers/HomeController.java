package com.deepak.microservices.APIGatewayService.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Mono<String> home() {
        return Mono.just("Welcome to API Gateway! Authentication successful.");
    }

//    @GetMapping("/user")
//    public Mono<Map<String, Object>> user(@AuthenticationPrincipal OAuth2User principal) {
//        if (principal == null) {
//            return Mono.just(Collections.singletonMap("error", "Not authenticated"));
//        }
//        return Mono.just(principal.getAttributes());
//    }

    @GetMapping("/home")
    public Mono<Map<String, Object>> homeInfo(@AuthenticationPrincipal OAuth2User principal) {
        return Mono.just(Map.of(
                "message", "Successfully authenticated!",
                "user", principal != null ? principal.getName() : "Anonymous",
                "timestamp", System.currentTimeMillis()
        ));
    }
}