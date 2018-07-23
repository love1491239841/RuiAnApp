package com.example.ruianapp.bean;

import java.io.Serializable;

public class MyPlan implements Serializable {
    private String category;
    private String title;
    private String timeFrom;
    private String timeTo;
    private String employees;
    private int status;
    private String content;

    public MyPlan(String category, String title, String timeFrom, String timeTo, String employees, int status, String content) {
        this.category = category;
        this.title = title;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.employees = employees;
        this.status = status;
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getEmployees() {
        return employees;
    }

    public void setEmployees(String employees) {
        this.employees = employees;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
