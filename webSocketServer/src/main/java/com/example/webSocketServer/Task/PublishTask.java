package com.example.webSocketServer.Task;

import com.example.webSocketServer.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class PublishTask {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void publish() {
        Message reply = new Message("This is message");
        System.out.println("publish called");
        simpMessagingTemplate.convertAndSend("/queue/notice", reply);
    }
}
