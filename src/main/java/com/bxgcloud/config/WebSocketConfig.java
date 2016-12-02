package com.bxgcloud.config;

import com.bxgcloud.controller.ChatController;
import com.bxgcloud.repository.UserinfoRepository;
import com.bxgcloud.service.MessageChatService;
import com.bxgcloud.service.UserinfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by gaobin on 2016/11/15.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(systemWebSocketHandler(),"/websocket");
    }
    @Bean
    public WebSocketHandler systemWebSocketHandler(){
        return  new ChatController();
    }
}
