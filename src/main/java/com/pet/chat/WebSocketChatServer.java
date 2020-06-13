package com.pet.chat;

import com.alibaba.fastjson.JSON;
import com.pet.model.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class WebSocketChatServer {
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll (Message message) {
        message.setCurrentSessions(onlineSessions.size());
        for (Session s : onlineSessions.values()) {
            try {
                s.getBasicRemote().sendText(JSON.toJSONString(message));
                System.out.println(String.format("Sending message '%s' to session #%s by [%s]", message.getContent(), s.getId(), message.getSender()));
            } catch (IOException e) {
                System.out.println(String.format("Error while sending message to session %s. Exception is: %s", s.getId(), e.toString()));
            }
        }
    }

    @OnOpen
    public static void onOpen (Session session, @PathParam("username") String username) {
        String sessionId = session.getId();
        onlineSessions.put(sessionId, session);
        Message message = new Message("Enter", username, "Connected!", onlineSessions.size());
        sendMessageToAll(message);
    }

    @OnMessage
    public static void onMessage (Session session, Message message) {
        message.setType("CHAT");
        sendMessageToAll(message);
    }

    @OnClose
    public static void onClose (Session session, @PathParam("username") String username) {
        String sessionId = session.getId();
        onlineSessions.remove(sessionId);
        Message message = new Message("LEAVE", username, "Disconnected!", onlineSessions.size());
        sendMessageToAll(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
}
