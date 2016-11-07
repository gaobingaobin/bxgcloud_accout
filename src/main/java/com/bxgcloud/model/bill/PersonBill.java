package com.bxgcloud.model.bill;




import com.bxgcloud.model.UserInfo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gaobin on 2016/10/26.
 */
@Entity
public class PersonBill {
    @Id
    @GeneratedValue
    Integer id;

    /**
     *账单人
     */
    @ManyToOne
    @JoinColumn(name="user_id")
    UserInfo userInfo;
    /**
     * 账单类型
    */
    @Column
    String type;
    /**
     * 账单金额
     */
    @Column
    Float money;
    /**
     * 备注
     */
    @Column
    String remark;
    /**
     * 账单生成日期
     */
    @Column
    Date billDate;



}
