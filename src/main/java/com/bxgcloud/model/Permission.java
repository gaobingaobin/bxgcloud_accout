package com.bxgcloud.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: gaobin
 * Date: 2017/2/15
 * Time: 9:55
 * To change this template use File | Settings | File Templates.
 * Description:权限表
 */
@Entity
@Table(name="permission")
public class Permission {
    @Id
    @GeneratedValue
    Integer id;
    /**
     *权限名称
     */
    @Column
    String name;
    /**
     * 权限标签
     */
    @Column
    String label;

    /**
     * 菜单
     */
    @ManyToOne
    @JoinColumn(name = "menu_id")
    Menu menu;







}
