package com.example.ruianapp.bean;

import org.litepal.crud.DataSupport;

/**
 * Created by zhanghx on 2018/5/20.
 */

public class Plan extends DataSupport{
    private String name;
    private String time;
    private String content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
