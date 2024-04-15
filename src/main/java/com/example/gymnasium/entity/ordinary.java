package com.example.gymnasium.entity;


import java.io.Serializable;

public class ordinary implements Serializable {
    private Integer userid;

    private String ordinarynum;

    private static final long serialVersionUID = 1L;

    public String getOrdinarynum() {
        return ordinarynum;
    }

    public void setOrdinarynum(String ordinarynum) {
        this.ordinarynum = ordinarynum;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}