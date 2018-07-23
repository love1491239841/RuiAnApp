package com.example.ruianapp.bean;

import java.io.Serializable;

public class MyLeave implements Serializable {
    private String persons;
    private String timeFrom;
    private String timeTo;
    private int status;
    private String reason;
    private String delegation;

    public MyLeave(String persons, String timeFrom, String timeTo, int status, String reason, String delegation) {
        this.persons = persons;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.status = status;
        this.reason = reason;
        this.delegation = delegation;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }
}
