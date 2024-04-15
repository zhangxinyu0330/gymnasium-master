package com.example.gymnasium.entity;

import java.io.Serializable;

public class teacher implements Serializable {
    private Integer userid;

    private String teachnumber;

    private String colleague;

    private static final long serialVersionUID = 1L;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTeachnumber() {
        return teachnumber;
    }

    public void setTeachnumber(String teachnumber) {
        this.teachnumber = teachnumber == null ? null : teachnumber.trim();
    }

    public String getColleague() {
        return colleague;
    }

    public void setColleague(String colleague) {
        this.colleague = colleague == null ? null : colleague.trim();
    }
}