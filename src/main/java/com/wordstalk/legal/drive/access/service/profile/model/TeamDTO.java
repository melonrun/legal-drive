package com.wordstalk.legal.drive.access.service.profile.model;

import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by guoyong on 2018/1/20.
 */
public class TeamDTO {

    private long id;
    @NotBlank
    private String teamName;
    private LocalDateTime createTime;

    public TeamDTO(){

    }

    public TeamDO toDO(long ownerId){
        TeamDO teamDO = new TeamDO();
        teamDO.setTeamName(this.getTeamName());
        teamDO.setOwnerId(ownerId);
        return teamDO;
    }

    public TeamDTO(TeamDO teamDO){
        this.id = teamDO.getId();
        this.teamName = teamDO.getTeamName();
        this.createTime = teamDO.getCreateTime();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
