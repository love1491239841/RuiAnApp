package com.example.ruianapp.bean;

import java.util.Date;

public class Signin {
    private int id;
    private int operator;
    private String addTime;
    private String type;
    private String longitude;
    private String latitude;

    public Signin(int id, int operator, String addTime, String type, String longitude, String latitude) {
        this.id = id;
        this.operator = operator;
        this.addTime = addTime;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
