package com.bxgcloud.controller;


import com.bxgcloud.service.MessageChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class ChatController implements WebSocketHandler {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketController对象。
    private static CopyOnWriteArraySet<ChatController> webSocketSet = new CopyOnWriteArraySet<ChatController>();
    private static Map<String,WebSocketSession> sessionMap = new ConcurrentHashMap<String, WebSocketSession>();
    @Autowired
    private MessageChatService messageChatService;

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ChatController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ChatController.onlineCount--;
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        String urlquery = webSocketSession.getUri().getQuery();
        String[] users = urlquery.split("&");
        String from = users[0];
        String to = users[1];
        addOnlineCount();
        sessionMap.put(from,webSocketSession);
        WebSocketSession sessionto = sessionMap.get(to);
        if(sessionto!=null){
            webSocketSession.sendMessage(new TextMessage("该用户在线"));//接受人的状态  上线
        }else{
            webSocketSession.sendMessage(new TextMessage("该用户下线"));
        }
        System.out.println("有一连接进入！当前在线人数为" + getOnlineCount());
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        try {
            String urlquery = webSocketSession.getUri().getQuery();
            String[] users = urlquery.split("&");
            String from = users[0];
            String to = users[1];
            Long readtype = 0L;
            WebSocketSession sessionto = sessionMap.get(to);
            WebSocketSession sessionfrom = sessionMap.get(from);
            if(sessionto!=null){
                readtype = 1L;
                sessionto.sendMessage(webSocketMessage);
                sessionfrom.sendMessage(webSocketMessage);
                System.out.print(webSocketMessage.getPayload());
            }else {
                webSocketSession.sendMessage(new TextMessage("该用户不在线"));
            }
           messageChatService.saveMessage(from,to,webSocketMessage,readtype);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        // 添加处理错误的操作
        System.out.println(throwable.getMessage());
        System.out.println(throwable.getCause());
        throwable.printStackTrace();

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        String urlquery = webSocketSession.getUri().getQuery();
        String[] users = urlquery.split("&");
        String from = users[0];
        sessionMap.remove(from);
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


}