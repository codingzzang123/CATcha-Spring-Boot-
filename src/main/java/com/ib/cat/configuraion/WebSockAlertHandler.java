package com.ib.cat.configuraion;

import com.ib.cat.dto.member.Auth;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

@Component
public class WebSockAlertHandler extends TextWebSocketHandler {

    private Map<String,WebSocketSession> userSessionsMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        userSessionsMap.put(currentUserName(session),session);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        userSessionsMap.remove(currentUserName(session),session);
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg =  message.getPayload();
        for(WebSocketSession s: userSessionsMap.values()){
            s.sendMessage(new TextMessage("this is example"));
        }
    }

    private String currentUserName(WebSocketSession session){
        Map<String ,Object> httpSession = session.getAttributes();

        Collection<Object> values = httpSession.values(); //System.out.println("Collection<Object> : "+values );

        Auth loginUser = (Auth)httpSession.get("auth");
        return loginUser.getName();
    }
}
