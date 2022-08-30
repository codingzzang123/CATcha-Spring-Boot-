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

    /* 로그인 한 user -> map으로 관리, 1:1 통신 */
    private Map<String,WebSocketSession> userSessionsMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        userSessionsMap.put(currentUserName(session),session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        userSessionsMap.remove(currentUserName(session),session);
    }

    private String currentUserName(WebSocketSession session){
        Map<String ,Object> maps = session.getAttributes();

        /* Login시 session에 등록된 auth객체 가져오기 */
        Auth loginUser = (Auth)maps.get("auth");
        return loginUser.getName();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg =  message.getPayload();

        if(StringUtils.isNotEmpty(msg)){
            StringTokenizer st = new StringTokenizer(msg,",");

            String cmd = st.nextToken();
            String sender = st.nextToken();
            String receiver = st.nextToken();
            String title = st.nextToken();

            if(title.length() > 10){
                title = title.substring(0,10) + "..";
            }
            WebSocketSession receiverSession = userSessionsMap.get(receiver);

            String prev = "<strong>"+sender+"</strong>님이 [<strong>"+ title+"</strong>]";

            if(receiverSession != null){
                TextMessage tmpMsg = null;
                switch (cmd){
                    case "like" :
                        tmpMsg = new TextMessage(prev + "게시글에<br> 좋아요를 눌렀습니다.");
                        break;
                    case "comment" :
                        tmpMsg = new TextMessage(prev + "게시글에<br> 댓글을 달았습니다." );
                        break;
                    case "reply" :
                        tmpMsg = new TextMessage(prev + "댓글에<br>답글을 달았습니다." );
                        break;
                }
                receiverSession.sendMessage(tmpMsg);
            }
        }
    }

}