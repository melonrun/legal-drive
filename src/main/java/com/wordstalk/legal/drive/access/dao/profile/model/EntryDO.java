package com.wordstalk.legal.drive.access.dao.profile.model;

/**
 * Created by y on 2018/1/2.
 */
public class EntryDO {

    private long id;
    private String userName;
    private String userPass;
    private String roleNameDisplay;
    private long roleId;
    private String teamList;
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

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getTeamList() {
        return teamList;
    }

    public void setTeamList(String teamList) {
        this.teamList = teamList;
    }

    @Override
    public String toString() {
        return "EntryDO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", roleNameDisplay='" + roleNameDisplay + '\'' +
                ", roleId=" + roleId +
                ", teamList='" + teamList + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", loginIp='" + loginIp + '\'' +
                '}';
    }
}
