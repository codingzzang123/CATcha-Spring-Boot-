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

            String[] strs = msg.split(",");

            String cmd = strs[0];
            String replyWriter = strs[1];
            String boardWriter = strs[2];
            String title = strs[3];

            if(title.length() > 10){
                title = title.substring(0,Math.min(title.length(),10));
                title += "..";
            }

            WebSocketSession replyWriterSession =
                    userSessionsMap.get(replyWriter);

            WebSocketSession boardWriterSession =
                        userSessionsMap.get(boardWriter);

            System.out.println("replyWriterSession = "+replyWriterSession);
            System.out.println("boardWriterSession = "+boardWriterSession);

            if ("like".equals(cmd)&&boardWriterSession !=null){

                TextMessage tmpMsg = new TextMessage(
                        "<strong>"+replyWriter+"</strong>"+"님이 [" +
                                "<strong>"+ title+"</strong>" +"] 게시글에<br> 좋아요를 눌렀습니다."
                );
                boardWriterSession.sendMessage(tmpMsg);
            }else if("comment".equals(cmd)&&boardWriterSession !=null){
                TextMessage tmpMsg = new TextMessage(
                        "<strong>"+replyWriter+"</strong>"+"님이 [" +
                                "<strong>"+ title+"</strong>" +"] 게시글에<br> 댓글을 달았습니다."
                );
                boardWriterSession.sendMessage(tmpMsg);
            }else if("reply".equals(cmd)&&boardWriterSession !=null){
                TextMessage tmpMsg = new TextMessage(
                        "<strong>"+replyWriter+"</strong>님이 [<strong>"+ title+"</strong>] 댓글에"+
                                "<br>답글을 달았습니다."
                );
                boardWriterSession.sendMessage(tmpMsg);
            }
        }
    }

}
