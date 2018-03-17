package com.wordstalk.legal.drive.access.dao.profile.model;

/**
 * Created by guoyong on 2018/1/20.
 */
public class UserShowDO {
    private long userId;
    private long roleId;
    private String userName;
    private String roleName;
    private String teamList;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getTeamList() {
        return teamList;
    }

    public void setTeamList(String teamList) {
        this.teamList = teamList;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserShowDO{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", userName='" + userName + '\'' +
                ", roleName='" + roleName + '\'' +
                ", teamList='" + teamList + '\'' +
                '}';
    }
}
