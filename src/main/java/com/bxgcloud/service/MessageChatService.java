package com.bxgcloud.service;

import com.bxgcloud.model.UserInfo;
import com.bxgcloud.model.bill.MessageChat;
import com.bxgcloud.repository.MessageChatRepository;
import com.bxgcloud.repository.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gaobin on 2016/12/1.
 */
@Service
public class MessageChatService {
    @Autowired
    private UserinfoRepository userinfoRepository;
    @Autowired
    private MessageChatRepository messageChatRepository;

    public void saveMessage(String from, String to, String message,Long redytype){
        MessageChat messageChat = new MessageChat();
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        UserInfo fromUser = userinfoRepository.findOne(Integer.parseInt(from));
        UserInfo toUser =  userinfoRepository.findOne(Integer.parseInt(to));
        messageChat.setFromUser(fromUser);
        messageChat.setToUser(toUser);
        messageChat.setMessage(message);
        messageChat.setToDate(date);
        messageChat.setReadtype(redytype);
        messageChatRepository.save(messageChat);
    }
}
