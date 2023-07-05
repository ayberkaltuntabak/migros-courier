package com.migros.courrierLocation.configuration;

import com.migros.courrierLocation.controller.CourierStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    private final CourierStreamHandler courierStreamHandler;

    @Autowired
    public WebSocketConfig(CourierStreamHandler courierStreamHandler) {
        this.courierStreamHandler = courierStreamHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(courierStreamHandler, "/websocket");
    }
}

