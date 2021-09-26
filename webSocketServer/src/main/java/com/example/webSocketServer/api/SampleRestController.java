package com.example.webSocketServer.api;

import com.example.webSocketServer.model.Message;
import com.example.webSocketServer.model.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {
    @GetMapping("/hello")
    String getHello() {
        return "Hello World";
    }

    @MessageMapping("/hello")
    @SendTo("/topic/hi")
    public Message greeting(User user) throws Exception {

        return new Message("Message from server: Hello " + user.getName());
    }
}
