package com.example.gymnasium.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class rule implements Serializable {
    private Integer ruleid;

    private Integer venueid;

    private Integer courttype;

    private Integer holidayornot;

    private Integer begintime;

    private Integer endtime;

    private Integer capacity;

    private BigDecimal price;

    private BigDecimal discount;

    private String userstatus;

    private Integer cardgrade;

    private static final long serialVersionUID = 1L;

    public Integer getRuleid() {
        return ruleid;
    }

    public void setRuleid(Integer ruleid) {
        this.ruleid = ruleid;
    }

    public Integer getVenueid() {
        return venueid;
    }

    public void setVenueid(Integer venueid) {
        this.venueid = venueid;
    }

    public Integer getCourttype() {
        return courttype;
    }

    public void setCourttype(Integer courttype) {
        this.courttype = courttype;
    }

    public Integer getHolidayornot() {
        return holidayornot;
    }

    public void setHolidayornot(Integer holidayornot) {
        this.holidayornot = holidayornot;
    }

    public Integer getBegintime() {
        return begintime;
    }

    public void setBegintime(Integer begintime) {
        this.begintime = begintime;
    }

    public Integer getEndtime() {
        return endtime;
    }

    public void setEndtime(Integer endtime) {
        this.endtime = endtime;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus == null ? null : userstatus.trim();
    }

    public Integer getCardgrade() {
        return cardgrade;
    }

    public void setCardgrade(Integer cardgrade) {
        this.cardgrade = cardgrade;
    }
}