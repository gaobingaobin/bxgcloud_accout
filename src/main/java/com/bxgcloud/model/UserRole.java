package com.bxgcloud.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: gaobin
 * Date: 2017/2/15
 * Time: 10:43
 * To change this template use File | Settings | File Templates.
 * Description: 人员与角色关联实体
 */
public class UserRole {
    @Id
    @GeneratedValue
    Integer id;
    /**
     * 用户
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserInfo user;
    /**
     * 角色
     */
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;


}
