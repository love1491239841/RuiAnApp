package com.example.ruianapp.bean;

import org.litepal.crud.DataSupport;

public class EnterprisesList extends DataSupport{
    private String name;
    private String legal;
    private String address;
    private String phone;
    private String email;
    private String jd;
    private String wd;

    public EnterprisesList(String name, String legal, String address, String phone, String email, String jd, String wd) {
        this.name = name;
        this.legal = legal;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.jd = jd;
        this.wd = wd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegal() {
        return legal;
    }

    public void setLegal(String legal) {
        this.legal = legal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
