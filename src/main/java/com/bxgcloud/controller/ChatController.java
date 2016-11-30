package com.bxgcloud.controller;


import com.bxgcloud.model.UserInfo;
import com.bxgcloud.model.bill.MessageChat;
import com.bxgcloud.repository.UserinfoRepository;
import com.bxgcloud.util.SessionMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/{from}/{to}")
@Component
public class ChatController {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketController对象。
    private static CopyOnWriteArraySet<ChatController> webSocketSet = new CopyOnWriteArraySet<ChatController>();
    private static Map<String,String> userMsgMap = null;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String from; //发送人
    private String to;  //接收人
    @Autowired
    private UserinfoRepository userinfoRepository;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "from")String from, @PathParam(value = "to")String to) throws IOException {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();
        //在线数加1
        this.from = from;
        this.to = to;
        SessionMap.getSessionMap().put(from,session);
        Session sessionto = SessionMap.getSessionMap().get(to);
        if(sessionto!=null){
            sendMessage("在线");//接受人的状态  上线
        }else{
            sendMessage("下线");
        }

        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        //群发消息
        for (ChatController item : webSocketSet) {
            try {
                item.sendMessage("当前在线人数为" + getOnlineCount());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            MessageChat messageChat = new MessageChat();
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            UserInfo fromUser = userinfoRepository.findOne(Integer.parseInt(from));
            UserInfo toUser = userinfoRepository.findOne(Integer.parseInt(to));
            messageChat.setFrom(fromUser);
            messageChat.setTo(toUser);
            messageChat.setMessage(message);
            messageChat.setToDate(date);

        /*    Session sessionto = SessionMap.getSessionMap().get(to);
            if(sessionto!=null){
                messageChat.setReadtype("1");
               *//* sysLogger.info("*** *** WebSocket opened to ship " + to);*//*
                sessionto.getBasicRemote().sendText(JSONArray.toJSONString(messageChat));
                session.getBasicRemote().sendText("online");
            }else {
                messageChat.setReadtype("0");
                session.getBasicRemote().sendText("offline");
            }

            chatInfoServer.insert(messageChat);*/
        } catch (IOException e) {
            e.printStackTrace();
        }


   /* System.out.println("来自客户端的消息:" + message);

    //群发消息
    for (ChatController item : webSocketSet) {
        try {
            item.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}

    /**
     * 发生错误时调用
     @OnError
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误");
     error.printStackTrace();
     }


     public void sendMessage(String message) throws IOException {
     this.session.getBasicRemote().sendText(message);
     //this.session.getAsyncRemote().sendText(message);
     }


     /**
      * 群发自定义消息
      * */
    public static void sendInfo(String message) throws IOException {
        for (ChatController item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        ChatController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ChatController.onlineCount--;
    }
}