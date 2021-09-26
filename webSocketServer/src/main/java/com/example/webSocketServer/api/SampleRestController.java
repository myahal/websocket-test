package com.example.webSocketServer.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SampleRestController {
    @GetMapping
    String getHello() {
        return "Hello World";
    }
}
