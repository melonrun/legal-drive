package com.wordstalk.legal.drive.controller.profile.viewo;

/**
 * Created by y on 2018/1/14.
 */
public class LoginUserOutVO {

    private long id;
    private String userName;
    private String roleNameDisplay;
    private String roleNameEn;

    public LoginUserOutVO(){

    }

    public LoginUserOutVO(String userName){
        this.id = 0;
        this.userName = userName;
    }

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
        return "LoginUserOutVO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", roleNameDisplay='" + roleNameDisplay + '\'' +
                ", roleNameEn='" + roleNameEn + '\'' +
                '}';
    }
}
