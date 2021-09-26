package com.example.webSocketServer.model;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.Map;

@Component
public class CustomHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected StompPrincipal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        String userName = request.getHeaders().get("sec-websocket-key").get(0);
        System.out.println("UserName: " + userName);
        return new StompPrincipal(userName);
    }
}