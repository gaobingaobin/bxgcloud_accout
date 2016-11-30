package com.bxgcloud.controller;


import com.bxgcloud.model.UserInfo;
import com.bxgcloud.model.bill.MessageChat;
import com.bxgcloud.repository.MessageChatRepository;
import com.bxgcloud.repository.UserinfoRepository;
import com.bxgcloud.util.GsonUtils;
import com.bxgcloud.util.SessionMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
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
    @Autowired
    private MessageChatRepository messageChatRepository;

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
            session.getBasicRemote().sendText("在线");//接受人的状态  上线
        }else{
            session.getBasicRemote().sendText("下线");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        SessionMap.getSessionMap().remove(from);
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
            messageChat.setFromUser(fromUser);
            messageChat.setToUser(toUser);
            messageChat.setMessage(message);
            messageChat.setToDate(date);
            Session sessionto = SessionMap.getSessionMap().get(to);
            if(sessionto!=null){
                messageChat.setReadtype(1L);
                sessionto.getBasicRemote().sendText(GsonUtils.toJson(messageChat));
                session.getBasicRemote().sendText("在线");
            }else {
                messageChat.setReadtype(0L);
                session.getBasicRemote().sendText("下线");
            }
            messageChatRepository.save(messageChat);
        } catch (IOException e) {
            e.printStackTrace();
        }

}

    /**
     * 发生错误时调用
     @OnError
     public void onError(Session session, Throwable error) {
     // 添加处理错误的操作
     System.out.println(t.getMessage());
     System.out.println(t.getCause());
     t.printStackTrace();
     }


     public void sendMessage(String message) throws IOException {
     this.session.getBasicRemote().sendText(message);
     //this.session.getAsyncRemote().sendText(message);
     }
     /**
      * 发生错误时调用
     @OnError
     */
     @OnError
     public void onError(Throwable t) {
     // 添加处理错误的操作
     System.out.println(t.getMessage());
     System.out.println(t.getCause());
     t.printStackTrace();
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