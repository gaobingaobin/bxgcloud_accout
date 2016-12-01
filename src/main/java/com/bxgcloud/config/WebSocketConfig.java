package com.bxgcloud.config;

import com.bxgcloud.repository.UserinfoRepository;
import com.bxgcloud.service.MessageChatService;
import com.bxgcloud.service.UserinfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * Created by gaobin on 2016/11/15.
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public MessageChatService UserinfoService(){
        return new MessageChatService();
    }
}
