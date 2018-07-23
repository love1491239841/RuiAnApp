package com.example.ruianapp.bean;

/**
 * Created by zhanghx on 2018/5/17.
 */

public class Inspect {
    private String name;
    private String time;

    public Inspect(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
