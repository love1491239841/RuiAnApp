package com.example.ruianapp.bean;

import java.io.Serializable;

public class MyTask implements Serializable {
    private String name;
    private String timeFrom;
    private String timeTo;
    private String comeFrom;
    private String comments;
    private boolean status;

    public MyTask(String name, String timeFrom, String timeTo, String comeFrom, String comments, boolean status) {
        this.name = name;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.comeFrom = comeFrom;
        this.comments = comments;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
