package com.example.ruianapp.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/19 0019.
 */

public class Gctg implements Serializable {
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
    private String cljzsj;
    private String other;
    private String qfr;
    private String qfdw;
    private String qfrq;
    private String qsyj;
    private String qsr;
    private String qsrq;
    private String fcyj;
    private String fcr;
    private String fcrq;
    private String cs;
    private String addTime;
    private int userId;
    private String updateIds;

    public Gctg(int id, String gcmc, String bh, String gcdz, String fbdw, String sgdw, String dgdw, String zmr, String fssj, String problem, String cljzsj, String other, String qfr, String qfdw, String qfrq, String qsyj, String qsr, String qsrq, String fcyj, String fcr, String fcrq, String cs, String addTime, int userId, String updateIds) {
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
        this.cljzsj = cljzsj;
        this.other = other;
        this.qfr = qfr;
        this.qfdw = qfdw;
        this.qfrq = qfrq;
        this.qsyj = qsyj;
        this.qsr = qsr;
        this.qsrq = qsrq;
        this.fcyj = fcyj;
        this.fcr = fcr;
        this.fcrq = fcrq;
        this.cs = cs;
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

    public String getCljzsj() {
        return cljzsj;
    }

    public void setCljzsj(String cljzsj) {
        this.cljzsj = cljzsj;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

    public String getQsyj() {
        return qsyj;
    }

    public void setQsyj(String qsyj) {
        this.qsyj = qsyj;
    }

    public String getQsr() {
        return qsr;
    }

    public void setQsr(String qsr) {
        this.qsr = qsr;
    }

    public String getQsrq() {
        return qsrq;
    }

    public void setQsrq(String qsrq) {
        this.qsrq = qsrq;
    }

    public String getFcyj() {
        return fcyj;
    }

    public void setFcyj(String fcyj) {
        this.fcyj = fcyj;
    }

    public String getFcr() {
        return fcr;
    }

    public void setFcr(String fcr) {
        this.fcr = fcr;
    }

    public String getFcrq() {
        return fcrq;
    }

    public void setFcrq(String fcrq) {
        this.fcrq = fcrq;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
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
