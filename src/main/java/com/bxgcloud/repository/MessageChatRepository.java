package com.bxgcloud.repository;

import com.bxgcloud.model.bill.MessageChat;
import com.bxgcloud.model.bill.PersonBill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gaobin on 2016/11/30.
 */
interface MessageChatRepository extends JpaRepository<MessageChat,Integer> {
}
