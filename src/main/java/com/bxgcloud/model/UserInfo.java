package com.bxgcloud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by gaobin on 2016-08-12.
 */
@Entity
public class UserInfo {
    @Id
    @GeneratedValue
    Integer  id;

    @Column (length = 20, nullable = false)
    String username;

    @Column (length = 20, nullable = false)
    String realname;

    @Column(length = 20, nullable = false)
    String password;

    @Column(length = 24)
    String email;

    @Column(length = 24)
    String phone;

    @Column
    String weinxin; //微信号

    @Column
    String LiveCity; //现居城市

    @Column
    String introduction; //个人简介

    @Column
    Date registerDate; //注册日期


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeinxin() {
        return weinxin;
    }

    public void setWeinxin(String weinxin) {
        this.weinxin = weinxin;
    }

    public String getLiveCity() {
        return LiveCity;
    }

    public void setLiveCity(String liveCity) {
        LiveCity = liveCity;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
