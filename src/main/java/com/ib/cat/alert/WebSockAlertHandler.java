package com.ib.cat.alert;

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

    /* 로그인 한 user 전체 */ /* 1. 서버에 들어온 유저 닉네임 다 가져와보기 */
    private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();

    /* 1:1 통신 */
    private Map<String,WebSocketSession> userSessionsMap = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        userSessionsMap.put(currentUserName(session),session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        userSessionsMap.remove(currentUserName(session),session);
    }

    private String currentUserName(WebSocketSession session){
        Map<String ,Object> httpSession = session.getAttributes();

        Collection<Object> values = httpSession.values(); //System.out.println("Collection<Object> : "+values );

        Auth loginUser = (Auth)httpSession.get("auth");
        return loginUser.getName();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg =  message.getPayload(); //script에서 받은 message

        if(StringUtils.isNotEmpty(msg)){
            System.out.println("into if");

            String[] strs = msg.split(",");

            String cmd = strs[0];
            String replyWriter = strs[1];
            String boardWriter = strs[2];

            WebSocketSession replyWriterSession =
                    userSessionsMap.get(replyWriter);

            WebSocketSession boardWriterSession =
                        userSessionsMap.get(boardWriter);

            System.out.println("replyWriterSession = "+replyWriterSession);
            System.out.println("boardWriterSession = "+boardWriterSession);

            if ("like".equals(cmd)&&boardWriterSession !=null){
                System.out.println("좋아요 onMessage");

                TextMessage tmpMsg = new TextMessage(
                        replyWriter+"님이"+boardWriter+"" +
                                "님의 게시글에 좋아요를 눌렀습니다"
                ); //왼쪽 주는사람(mouse), 오른쪽 받는사람(wendy)
                boardWriterSession.sendMessage(tmpMsg);
            }
        }
    }

}
