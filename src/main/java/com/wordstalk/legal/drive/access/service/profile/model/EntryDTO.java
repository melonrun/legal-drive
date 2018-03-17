package com.wordstalk.legal.drive.access.service.profile.model;

import com.wordstalk.legal.drive.access.dao.profile.model.EntryDO;
import com.wordstalk.legal.drive.controller.profile.viewo.LoginUserOutVO;

/**
 * Created by y on 2018/1/7.
 */
public class EntryDTO {

    private long id;
    private String userName;
    private String roleNameDisplay;
    private long roleId;
    private String teamList;

    public EntryDTO(){

    }

    public EntryDTO(String userName){
        this.id = 0;
        this.userName = userName;
    }

    public EntryDTO(EntryDO entryDO){
        this.id = entryDO.getId();
        this.userName = entryDO.getUserName();
        this.roleNameDisplay = entryDO.getRoleNameDisplay();
        this.roleId = entryDO.getRoleId();
        this.teamList = entryDO.getTeamList();
    }

    public LoginUserOutVO toVO() {
        LoginUserOutVO loginUserOutVO = new LoginUserOutVO();
        loginUserOutVO.setId(this.getId());
        loginUserOutVO.setRoleNameDisplay(this.getRoleNameDisplay());
        loginUserOutVO.setUserName(this.getUserName());
        return loginUserOutVO;
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

    @Override
    public String toString() {
        return "EntryDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", roleNameDisplay='" + roleNameDisplay + '\'' +
                '}';
    }

}
