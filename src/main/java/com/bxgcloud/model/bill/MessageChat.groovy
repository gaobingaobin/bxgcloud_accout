package com.bxgcloud.model.bill

import com.bxgcloud.model.UserInfo

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Temporal
import javax.persistence.TemporalType

/**
 * Created by gaobin on 2016/11/30.
 */
@Entity
class MessageChat {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name="toUser")
    UserInfo toUser;

    @ManyToOne
    @JoinColumn(name="fromUser")
    UserInfo fromUser;

    @Column
    String message

   @Column
    String toDate

    /**
     * 是否收到标识  0 未收到 1 收到
    */
    @Column
    Long readtype

}
