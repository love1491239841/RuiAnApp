package com.example.ruianapp.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class News implements Serializable {
    private String title;
    private String addTime;
    private String content;

    public News(String title, String addTime, String content) {
        this.title = title;
        this.addTime = addTime;
        this.content = content;
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
}
