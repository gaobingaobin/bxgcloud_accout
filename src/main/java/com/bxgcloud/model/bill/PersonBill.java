package com.bxgcloud.model.bill;




import com.bxgcloud.model.UserInfo;
import com.google.gson.annotations.Expose;

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
    @Temporal(TemporalType.DATE)
    Date billDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
}
