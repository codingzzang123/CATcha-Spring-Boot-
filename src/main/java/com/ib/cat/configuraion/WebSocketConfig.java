package com.ib.cat.configuraion;

import com.ib.cat.alert.WebSockAlertHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private final WebSockAlertHandler webSockAlertHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSockAlertHandler, "/gs-websocket")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .withSockJS();
    }
}
