package com.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WebSocketChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketChatApplication.class, args);
    }
}
