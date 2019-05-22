package com.blizzard.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Description: ${Description}
 * @Author: Huan
 * @CreateTime: 2019-02-19 16:07
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
       webSocketHandlerRegistry.addHandler( myHandler(),"/ws").addInterceptors(new SelfHandShakeInterceptor());
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new SelfTextWebSocketHandler();
    }
}
