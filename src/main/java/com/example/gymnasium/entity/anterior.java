package com.example.gymnasium.entity;

import java.io.Serializable;

public class anterior implements Serializable {
    private Integer anteriorid;

    private String name;

    private String password;

    private static final long serialVersionUID = 1L;

    public Integer getAnteriorid() {
        return anteriorid;
    }

    public void setAnteriorid(Integer anteriorid) {
        this.anteriorid = anteriorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}