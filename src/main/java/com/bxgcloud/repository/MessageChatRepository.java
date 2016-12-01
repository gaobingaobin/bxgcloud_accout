package com.bxgcloud.repository;

import com.bxgcloud.model.bill.MessageChat;
import com.bxgcloud.model.bill.PersonBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gaobin on 2016/11/30.
 */
@Repository
public interface MessageChatRepository extends JpaRepository<MessageChat,Integer> {
}
