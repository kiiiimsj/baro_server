package com.wantchu.wantchu_server2.websocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

// ws://server[:port][/contextPath]/websocket/
@ServerEndpoint("/websocket")
public class WebsocketListener {

    @OnOpen
    public void handleConnect(Session session) {
        System.out.println("Client connect : " + session);
    }

    @OnMessage
    public void handleMessage(Session session, String message) {
        System.out.println(session + " : " + message);
        String[] tokens = message.split(":::");

        if(tokens[0].equals("connect")) {
            WebSocketSessionManager.addSession(tokens[1], session);
        } else if(tokens[0].equals("alert")) {
            WebSocketSessionManager.sendMessage(tokens[1]);
            // message:::USER:::JSONObject
        } else if(tokens[0].equals("message")) {
            WebSocketSessionManager.sendMessageToOnePerson(tokens[1], tokens[2]);
        }
    }

    @OnClose
    public void handleDisconnect(Session session) {
        System.out.println(session + "과의 연결 종료.");
        WebSocketSessionManager.removeSession(session);
    }

    @OnError
    public void handleError(Session session, Throwable throwable) {
        throwable.printStackTrace();
        WebSocketSessionManager.removeSession(session);
    }
}

// WebSocket webSocket = new WebSocket("ws://54.180.56.44:8080/websocket");
// 최초 연결 시 webSocket.send("connect:::" + "고유id값");  // connect : 최초 연결
// webSocket.onMessage() : 메시지 수신 처리
// webSocket.send("protocol:::message");

