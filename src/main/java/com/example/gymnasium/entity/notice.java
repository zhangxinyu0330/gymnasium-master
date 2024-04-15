package com.example.gymnasium.entity;

import java.io.Serializable;
import java.sql.Date;

public class notice implements Serializable {
    private Integer noticeid;

    private Date noticetime;

    private String content;

    private String title;

    private Integer state;

    private Integer venueid;

    private Integer noticeisdel;

    private static final long serialVersionUID = 1L;

    public Integer getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }

    public Date getNoticetime() {
        return noticetime;
    }

    public void setNoticetime(Date noticetime) {
        this.noticetime = noticetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getVenueid() {
        return venueid;
    }

    public void setVenueid(Integer venueid) {
        this.venueid = venueid;
    }

    public Integer getNoticeisdel() {
        return noticeisdel;
    }

    public void setNoticeisdel(Integer noticeisdel) {
        this.noticeisdel = noticeisdel;
    }
}