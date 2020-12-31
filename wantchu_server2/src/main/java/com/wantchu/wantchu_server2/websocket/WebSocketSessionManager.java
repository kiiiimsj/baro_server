package com.wantchu.wantchu_server2.websocket;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class WebSocketSessionManager {

    private static WebSocketSessionManager instance = new WebSocketSessionManager();

    private WebSocketSessionManager() {}

    private static HashMap<String, ArrayList<Session>> sessions = new HashMap<>();

    public static void addSession(String key, Session session) {
        if(sessions.get(key) == null) {
            System.out.println("sessions with key " + key + "is null. created new arraylist instance.");
            ArrayList<Session> sessionArrayList = new ArrayList<>();
            sessionArrayList.add(session);
            sessions.put(key, sessionArrayList);
            return;
        }
        System.out.println("sessions with key " + key + " is not null. added to sessions.");
        sessions.get(key).add(session);
    }

    public static void removeSession(Session session) {
        Set<String> keySet = sessions.keySet();
        Iterator<String> iterator = keySet.iterator();
        String key = null;
        while(iterator.hasNext()) {
            key = iterator.next();
            ArrayList<Session> sessionArrayList = sessions.get(key);
            Iterator<Session> sessionIterator = sessionArrayList.iterator();
            while(sessionIterator.hasNext()) {
                Session sessionToRemove = sessionIterator.next();
                if(sessionToRemove.equals(session)) {
                    sessions.get(key).remove(session);
                    break;
                }
            }
        }
    }

    public static void sendMessage(String message) {
        Set<String> keySet = sessions.keySet();
        Iterator<String> iterator = keySet.iterator();
        String key = null;
        while(iterator.hasNext()) {
            key = iterator.next();
            ArrayList<Session> sessionArrayList = sessions.get(key);
            Iterator<Session> sessionIterator = sessionArrayList.iterator();
            while(sessionIterator.hasNext()) {
                Session session = sessionIterator.next();
                try {
                    session.getBasicRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void sendMessageToOnePerson(String user, String message) {
        System.out.println("sendMessageToOnePerson. user : " + user + ", message : " + message);
        try {
            ArrayList<Session> sessionArrayList = sessions.get(user);
            Iterator<Session> sessionIterator = sessionArrayList.iterator();
            while(sessionIterator.hasNext()) {
                Session session = sessionIterator.next();
                session.getBasicRemote().sendText(message);
            }
        } catch(IOException e){
            e.printStackTrace();
        } catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    public static void sendMessageToOnePerson(String user) {
        try {
            ArrayList<Session> sessionArrayList = sessions.get(user);
            Iterator<Session> sessionIterator = sessionArrayList.iterator();
            while(sessionIterator.hasNext()) {
                System.out.println("들어옴");
                Session session = sessionIterator.next();
                session.getBasicRemote().sendText("pong");
            }
        } catch(IOException e){
            e.printStackTrace();
        } catch(NullPointerException e){
            e.printStackTrace();
        }
    }
}
