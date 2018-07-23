package com.example.ruianapp.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhanghx on 2018/6/9.
 */

public class Userinfo implements Serializable {
    private int id;
    private String loginName;
    private String realName;
    private String icon;
    private boolean status;
    private int roleId;
    private String telephone;
    private String email;
    private String comments;

    public Userinfo(int id, String loginName, String realName, String icon, boolean status, int roleId, String telephone, String email, String comments) {
        this.id = id;
        this.loginName = loginName;
        this.realName = realName;
        this.icon = icon;
        this.status = status;
        this.roleId = roleId;
        this.telephone = telephone;
        this.email = email;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
