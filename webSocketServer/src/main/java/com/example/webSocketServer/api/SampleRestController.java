package com.example.webSocketServer.api;

import com.example.webSocketServer.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class SampleRestController {
    @GetMapping("/hello")
    String getHello() {
        return "Hello World";
    }

    @MessageMapping("/message")
    @SendTo("/receive/message")
    public Message send(Message message) throws Exception {
        Thread.sleep(1000);
        return new Message(HtmlUtils.htmlEscape(message.getName()), HtmlUtils.htmlEscape(message.getStatement()));
    }
}
