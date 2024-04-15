package com.example.gymnasium.entity;

import java.io.Serializable;

public class venue implements Serializable {
    private Integer venueid;

    private String name;

    private String introduction;

    private Integer venueisdel;

    private byte[] picture;

    private static final long serialVersionUID = 1L;

    public Integer getVenueid() {
        return venueid;
    }

    public void setVenueid(Integer venueid) {
        this.venueid = venueid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getVenueisdel() {
        return venueisdel;
    }

    public void setVenueisdel(Integer venueisdel) {
        this.venueisdel = venueisdel;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}