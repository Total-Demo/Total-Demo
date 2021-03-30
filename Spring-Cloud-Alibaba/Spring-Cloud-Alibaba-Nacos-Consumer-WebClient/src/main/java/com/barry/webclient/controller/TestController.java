package com.barry.webclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/test")
    public Mono<String> test() {
        Mono<String> result = webClientBuilder.build()
                .get()
                .uri("http://nacos-provider/test/webclient")
                .retrieve()
                .bodyToMono(String.class);
        return result;
    }
}
