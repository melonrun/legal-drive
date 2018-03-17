package com.wordstalk.legal.drive.controller.profile.viewo;

import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;
import com.wordstalk.legal.drive.access.dao.profile.model.UserDO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
public class UserVO {

    @NotBlank
    private String userName;
    @NotBlank
    private String userPass;
    @Min(value = 1)
    private long roleId;
    @NotNull
    @Size(min=1)
    private List<Long> teamIds;
    private long userId;

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

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserDO toUserDO(){
        UserDO userDO = new UserDO();
        userDO.setId(this.getUserId());
        userDO.setUserName(this.getUserName());
        userDO.setUserPass(this.getUserPass());
        return userDO;
    }

    public List<Long> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Long> teamIds) {
        this.teamIds = teamIds;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", roleId=" + roleId +
                ", teamIds=" + teamIds +
                ", userId=" + userId +
                '}';
    }
}
