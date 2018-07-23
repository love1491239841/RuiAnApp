package com.example.ruianapp.bean;

public class NewUser {
    private String loginName;
    private String password;
    private String telephone;
    private String email;
    private String addTime;

    public NewUser(String loginName, String password, String telephone, String email, String addTime) {
        this.loginName = loginName;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
        this.addTime = addTime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
