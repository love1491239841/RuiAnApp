package com.example.ruianapp.bean;

/**
 * Created by Administrator on 2018/6/21 0021.
 */

public class Collect {
    private int id;
    private String type;
    private String name;
    private boolean status;

    public Collect(int id, String type, String name, boolean status) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
