package com.wordstalk.legaldrive.access.dao.profile.model;

/**
 * Created by y on 2018/1/2.
 */
public class WUserDO {

    private long id;
    private String userName;
    private String userPass;
    private String roleNameDisplay;
    private String roleNameEn;
    private String loginTime;
    private String loginIp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }


    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getRoleNameDisplay() {
        return roleNameDisplay;
    }

    public void setRoleNameDisplay(String roleNameDisplay) {
        this.roleNameDisplay = roleNameDisplay;
    }

    public String getRoleNameEn() {
        return roleNameEn;
    }

    public void setRoleNameEn(String roleNameEn) {
        this.roleNameEn = roleNameEn;
    }

    @Override
    public String toString() {
        return "WUserDO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", roleNameDisplay='" + roleNameDisplay + '\'' +
                ", roleNameEn='" + roleNameEn + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", loginIp='" + loginIp + '\'' +
                '}';
    }
}
