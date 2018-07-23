package com.example.ruianapp.bean;

/**
 * Created by zhanghx on 2018/5/16.
 */

public class Record {
    private int id;
    private String text;
    private String time;

    public Record(int id, String text, String time) {
        this.id = id;
        this.text = text;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
