package com.example.gymnasium.entity;

import java.io.Serializable;
import java.sql.Date;

public class reservation implements Serializable {
    private Integer reservationid;

    private Integer venueid;

    private Integer courtid;

    private Date date;

    private String state;

    private static final long serialVersionUID = 1L;

    public Integer getReservationid() {
        return reservationid;
    }

    public void setReservationid(Integer reservationid) {
        this.reservationid = reservationid;
    }

    public Integer getVenueid() {
        return venueid;
    }

    public void setVenueid(Integer venueid) {
        this.venueid = venueid;
    }

    public Integer getCourtid() {
        return courtid;
    }

    public void setCourtid(Integer courtid) {
        this.courtid = courtid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}