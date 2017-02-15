package com.bxgcloud.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: gaobin
 * Date: 2017/2/15
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Entity()
@Table(name = "role_permission")
public class RolePermission {
    @Id
    @GeneratedValue
    Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    Permission permission;

}
