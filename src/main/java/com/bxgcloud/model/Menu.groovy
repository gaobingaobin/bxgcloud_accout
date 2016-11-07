package com.bxgcloud.model

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.*

/**
 * @author shonve
 * @createDate 2015/8/14 10:17
 * @description
 */
@Entity
@Table(name = "menu")
class Menu {
    @Id
    @GeneratedValue
    Integer id;

    /**
     * 菜单显示的名称
     */
    @Column(length = 100)
    String name;

    /**
     * 图标标签
     */
    @Column(name = "icon_label",length = 100)
    String iconLabel;

    /**
     * 菜单连接
     */
    @Column(length = 100)
    String url;

    /**
     * 排序
     */
    @Column
    Integer sort;

    /**
     * 该菜单所在的父菜单
     */
    @ManyToOne
    @JoinColumn(name = "parent_id")
    Menu parentMenu;

    /**
     * 该菜单所拥有的所有子菜单
     */
    @OneToMany
    @JsonIgnore
    List<Menu> childrenMenus = new ArrayList<Menu>();
}
