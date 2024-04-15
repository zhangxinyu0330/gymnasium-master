package com.example.gymnasium.entity;

import javax.persistence.Lob;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Base64;

public class court implements Serializable {
    private Integer courtid;

    private Integer venueid;

    private Integer courttype;

    private String name;

    private Integer courtisdel;

    private BigDecimal courtx1;

    private BigDecimal courty1;

    private BigDecimal courtx2;

    private BigDecimal courty2;

    @Lob
    private byte[] picture;

    private static final long serialVersionUID = 1L;

    public Integer getCourtid() {
        return courtid;
    }

    public void setCourtid(Integer courtid) {
        this.courtid = courtid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCourtisdel() {
        return courtisdel;
    }

    public void setCourtisdel(Integer courtisdel) {
        this.courtisdel = courtisdel;
    }

    public BigDecimal getCourtx1() {
        return courtx1;
    }

    public void setCourtx1(BigDecimal courtx1) {
        this.courtx1 = courtx1;
    }

    public BigDecimal getCourty1() {
        return courty1;
    }

    public void setCourty1(BigDecimal courty1) {
        this.courty1 = courty1;
    }

    public BigDecimal getCourtx2() {
        return courtx2;
    }

    public void setCourtx2(BigDecimal courtx2) {
        this.courtx2 = courtx2;
    }

    public BigDecimal getCourty2() {
        return courty2;
    }

    public void setCourty2(BigDecimal courty2) {
        this.courty2 = courty2;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public void getBase64Image(){
        String base64String = Base64.getEncoder().encodeToString(this.picture);
        this.picture= base64String.getBytes();
    }
}