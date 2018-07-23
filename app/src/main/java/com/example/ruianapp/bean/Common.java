package com.example.ruianapp.bean;

import java.io.Serializable;
import java.util.Date;

public class Common implements Serializable {
    private int taskId;
    private String qymc;
    private String frdb;
    private String qydz;
    private String qylxdh;
    private String fwjg;
    private String fwjglxdh;
    private int jcry;
    private Date jcrq;
    private String cyjcry;
    private String zjjcyj;
    private Date addTime;

    public Common(int taskId, String qymc, String frdb, String qydz, String qylxdh, String fwjg, String fwjglxdh, int jcry, Date jcrq, String cyjcry, String zjjcyj, Date addTime) {
        this.taskId = taskId;
        this.qymc = qymc;
        this.frdb = frdb;
        this.qydz = qydz;
        this.qylxdh = qylxdh;
        this.fwjg = fwjg;
        this.fwjglxdh = fwjglxdh;
        this.jcry = jcry;
        this.jcrq = jcrq;
        this.cyjcry = cyjcry;
        this.zjjcyj = zjjcyj;
        this.addTime = addTime;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getFrdb() {
        return frdb;
    }

    public void setFrdb(String frdb) {
        this.frdb = frdb;
    }

    public String getQydz() {
        return qydz;
    }

    public void setQydz(String qydz) {
        this.qydz = qydz;
    }

    public String getQylxdh() {
        return qylxdh;
    }

    public void setQylxdh(String qylxdh) {
        this.qylxdh = qylxdh;
    }

    public String getFwjg() {
        return fwjg;
    }

    public void setFwjg(String fwjg) {
        this.fwjg = fwjg;
    }

    public String getFwjglxdh() {
        return fwjglxdh;
    }

    public void setFwjglxdh(String fwjglxdh) {
        this.fwjglxdh = fwjglxdh;
    }

    public int getJcry() {
        return jcry;
    }

    public void setJcry(int jcry) {
        this.jcry = jcry;
    }

    public Date getJcrq() {
        return jcrq;
    }

    public void setJcrq(Date jcrq) {
        this.jcrq = jcrq;
    }

    public String getCyjcry() {
        return cyjcry;
    }

    public void setCyjcry(String cyjcry) {
        this.cyjcry = cyjcry;
    }

    public String getZjjcyj() {
        return zjjcyj;
    }

    public void setZjjcyj(String zjjcyj) {
        this.zjjcyj = zjjcyj;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
