package com.springboot.demo.springboot.webscoket;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Service
@ServerEndpoint(value = "/websocket/{id}", encoders = {WebSocketEncoder.class})
public class WebSocketServer {
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    private static int onlineCount = 0;

    private Session session;
    private String id = "";

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        this.session = session;
        this.id = id;
        webSocketMap.put(id, this);
        addOnlineCount();
        System.out.println("[" + id + "]连接，当前在线人数为：" + getOnlineCount());
    }

    @OnClose
    public void onClose() {
        webSocketMap.remove(id);
        subOnlineCount();
        System.out.println("[" + id + "]关闭，当前在线人数为：" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("收到来自[" + id + "]的消息：" + message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    public static void sendMessageToPeer(WebSocketMessage webSocketMessage) {
        System.out.println("[" + webSocketMessage.getFromId() + "]发送消息到[" + webSocketMessage.getToId() + "]，消息内容：" + webSocketMessage.getMessage());
        if (webSocketMap.containsKey(webSocketMessage.getToId())) {
            try {
                webSocketMap.get(webSocketMessage.getToId()).session.getBasicRemote().sendObject(webSocketMessage);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        if (WebSocketServer.onlineCount > 0) {
            WebSocketServer.onlineCount--;
        }
    }

}