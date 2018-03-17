package com.wordstalk.legal.drive.access.dao.profile.model;

import com.wordstalk.legal.drive.access.service.profile.model.TeamDTO;

import java.time.LocalDateTime;

/**
 * Created by guoyong on 2018/1/20.
 */
public class TeamDO {

    private long id;
    private String teamName;
    private LocalDateTime createTime;
    private long ownerId;

    public TeamDO(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "TeamDO{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
