package com.bxgcloud.model.bill;




import com.bxgcloud.model.UserInfo;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by gaobin on 2016/10/26.
 */
@Entity
public class CommonBill {

    @Id
    @GeneratedValue
    Integer id;

    /**
     *账单人出钱人
     */
    @ManyToOne
    @JoinColumn(name="user_id")
    UserInfo userInfo;

    /**
     * 账单关联人（多个）
     */
    @OneToMany
    @Column
    Set<UserInfo> userInfos = new HashSet<UserInfo>();
    /**
     * 账单类型
     */
    @Column
    String type;
    /**
     * 备注
     */
    @Column
    String remark;
    /**
     * 账单金额
     */
    @Column
    Float money;

    /**
     * 账单生成日期
     */
    @Column
    Date billDate;
    /**
     * 每人分担金额
     */
    @Column
    Float perMoney;

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

    public Set<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(Set<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Float getPerMoney() {
        return perMoney;
    }

    public void setPerMoney(Float perMoney) {
        this.perMoney = perMoney;
    }
}
