package com.bxgcloud.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: gaobin
 * Date: 2017/2/15
 * Time: 9:56
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    Integer id;
    /**
     *角色名称
     */
    @Column
    String name;
    /**
     * 角色标签
     */
    @Column
    String label;


}
