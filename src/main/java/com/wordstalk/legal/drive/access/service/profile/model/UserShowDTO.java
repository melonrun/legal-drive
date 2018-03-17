package com.wordstalk.legal.drive.access.service.profile.model;

import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;
import com.wordstalk.legal.drive.access.dao.profile.model.UserShowDO;
import com.wordstalk.legal.drive.controller.common.CommonField;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by guoyong on 2018/1/20.
 */
public class UserShowDTO {

    private long userId;
    private String userName;
    private long roleId;
    private String roleName;
    private List<Long> teamIds;
    private String teamNames;

    public UserShowDTO() {
    }

    public UserShowDTO(UserShowDO userShowDO, Map<Long, TeamDO> teamDOMap) {
        this.userId = userShowDO.getUserId();
        this.userName = userShowDO.getUserName();
        this.roleName = userShowDO.getRoleName();
        this.roleId = userShowDO.getRoleId();
        this.teamIds = new ArrayList<>();
        if (CommonField.ROLE_PARTNER_DISPLAY.equals(roleName) || "0".equals(userShowDO.getTeamList())) {
            this.teamNames = CommonField.TEAM_ALL;
        } else if (StringUtils.isEmpty(userShowDO.getTeamList())) {
            this.teamNames = "";
        } else {
            String[] idArr = userShowDO.getTeamList().split(",");
            List<String> teamNameList = new ArrayList<>();
            for (String tId : idArr) {
                teamIds.add(Long.valueOf(tId));
                if (teamDOMap.containsKey(Long.valueOf(tId))) {
                    teamNameList.add(teamDOMap.get(Long.valueOf(tId)).getTeamName());
                }
            }
            this.teamNames = StringUtils.join(teamNameList.toArray(), "/");
        }
    }

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getTeamNames() {
        return teamNames;
    }

    public void setTeamNames(String teamNames) {
        this.teamNames = teamNames;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Long> teamIds) {
        this.teamIds = teamIds;
    }

    @Override
    public String toString() {
        return "UserShowDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", teamIds=" + teamIds +
                ", teamNames='" + teamNames + '\'' +
                '}';
    }
}
