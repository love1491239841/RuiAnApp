package com.example.ruianapp.bean;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/14 0014.
 */

public class Gcfk extends LitePalSupport implements Serializable {
    private int id;
    private String gcmc;
    private String bh;
    private String gcdz;
    private String fbdw;
    private String sgdw;
    private String dgdw;
    private String zmr;
    private String fssj;
    private String problem;
    private String dx;
    private double xx;
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
    private String jd;
    private String wd;
    private boolean submited;
    private String word;
    private String pdf;

    public Gcfk(int id, String gcmc, String bh, String gcdz, String fbdw, String sgdw, String dgdw, String zmr, String fssj, String problem, String dx, double xx, String qfr, String qfdw, String qfrq, String sjr, String sjrq, String sjr2, String sjrq2, String addTime, int userId, String updateIds, String jd, String wd, boolean submited, String word, String pdf) {
        this.id = id;
        this.gcmc = gcmc;
        this.bh = bh;
        this.gcdz = gcdz;
        this.fbdw = fbdw;
        this.sgdw = sgdw;
        this.dgdw = dgdw;
        this.zmr = zmr;
        this.fssj = fssj;
        this.problem = problem;
        this.dx = dx;
        this.xx = xx;
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
        this.jd = jd;
        this.wd = wd;
        this.submited = submited;
        this.word = word;
        this.pdf = pdf;
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

    public String getFssj() {
        return fssj;
    }

    public void setFssj(String fssj) {
        this.fssj = fssj;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public double getXx() {
        return xx;
    }

    public void setXx(double xx) {
        this.xx = xx;
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

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public boolean isSubmited() {
        return submited;
    }

    public void setSubmited(boolean submited) {
        this.submited = submited;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
