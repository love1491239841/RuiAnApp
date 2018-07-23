package com.example.ruianapp.bean;

import java.io.Serializable;

public class MyInform implements Serializable {
    private String sender;
    private String type;
    private String title;
    private String addTime;
    private String content;
    private int status;

    public MyInform(String sender, String type, String title, String addTime, String content, int status) {
        this.sender = sender;
        this.type = type;
        this.title = title;
        this.addTime = addTime;
        this.content = content;
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
