package com.example.ruianapp.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/19 0019.
 */

public class Gclx implements Serializable {
    private int id;
    private String gcmc;
    private String bh;
    private String gcdz;
    private String fbdw;
    private String sgdw;
    private String dgdw;
    private String zmr;
    private String problem;
    private String qfr;
    private String qfdw;
    private String qfrq;
    private String sjr;
    private String sjrq;
    private String sjr2;
    private String sjrq2;
    private String addTime;
    private int userId;
    private String updateIds;

    public Gclx(int id, String gcmc, String bh, String gcdz, String fbdw, String sgdw, String dgdw, String zmr, String problem, String qfr, String qfdw, String qfrq, String sjr, String sjrq, String sjr2, String sjrq2, String addTime, int userId, String updateIds) {
        this.id = id;
        this.gcmc = gcmc;
        this.bh = bh;
        this.gcdz = gcdz;
        this.fbdw = fbdw;
        this.sgdw = sgdw;
        this.dgdw = dgdw;
        this.zmr = zmr;
        this.problem = problem;
        this.qfr = qfr;
        this.qfdw = qfdw;
        this.qfrq = qfrq;
        this.sjr = sjr;
        this.sjrq = sjrq;
        this.sjr2 = sjr2;
        this.sjrq2 = sjrq2;
        this.addTime = addTime;
        this.userId = userId;
        this.updateIds = updateIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGcmc() {
        return gcmc;
    }

    public void setGcmc(String gcmc) {
        this.gcmc = gcmc;
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh;
    }

    public String getGcdz() {
        return gcdz;
    }

    public void setGcdz(String gcdz) {
        this.gcdz = gcdz;
    }

    public String getFbdw() {
        return fbdw;
    }

    public void setFbdw(String fbdw) {
        this.fbdw = fbdw;
    }

    public String getSgdw() {
        return sgdw;
    }

    public void setSgdw(String sgdw) {
        this.sgdw = sgdw;
    }

    public String getDgdw() {
        return dgdw;
    }

    public void setDgdw(String dgdw) {
        this.dgdw = dgdw;
    }

    public String getZmr() {
        return zmr;
    }

    public void setZmr(String zmr) {
        this.zmr = zmr;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getQfr() {
        return qfr;
    }

    public void setQfr(String qfr) {
        this.qfr = qfr;
    }

    public String getQfdw() {
        return qfdw;
    }

    public void setQfdw(String qfdw) {
        this.qfdw = qfdw;
    }

    public String getQfrq() {
        return qfrq;
    }

    public void setQfrq(String qfrq) {
        this.qfrq = qfrq;
    }

    public String getSjr() {
        return sjr;
    }

    public void setSjr(String sjr) {
        this.sjr = sjr;
    }

    public String getSjrq() {
        return sjrq;
    }

    public void setSjrq(String sjrq) {
        this.sjrq = sjrq;
    }

    public String getSjr2() {
        return sjr2;
    }

    public void setSjr2(String sjr2) {
        this.sjr2 = sjr2;
    }

    public String getSjrq2() {
        return sjrq2;
    }

    public void setSjrq2(String sjrq2) {
        this.sjrq2 = sjrq2;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUpdateIds() {
        return updateIds;
    }

    public void setUpdateIds(String updateIds) {
        this.updateIds = updateIds;
    }
}
