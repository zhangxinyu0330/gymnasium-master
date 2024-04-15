package com.example.gymnasium.entity;

import java.io.Serializable;

public class user implements Serializable {
    private Integer userid;

    private String username;

    private String password;

    private Integer status;

    private Integer cardid;

    private Integer phonenumber;

    private byte[] headpic;

    private static final long serialVersionUID = 1L;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCardid() {
        return cardid;
    }

    public void setCardid(Integer cardid) {
        this.cardid = cardid;
    }

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

    public byte[] getHeadpic() {
        return headpic;
    }

    public void setHeadpic(byte[] headpic) {
        this.headpic = headpic;
    }
}