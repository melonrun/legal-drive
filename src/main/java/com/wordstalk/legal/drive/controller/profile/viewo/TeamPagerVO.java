package com.wordstalk.legal.drive.controller.profile.viewo;

import com.wordstalk.legal.drive.access.service.profile.model.TeamDTO;

import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
public class TeamPagerVO {

    private List<TeamDTO> teams;
    private long totalItem;
    private long totalPage;

    public List<TeamDTO> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamDTO> teams) {
        this.teams = teams;
    }

    public long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(long totalItem) {
        this.totalItem = totalItem;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "TeamPagerVO{" +
                "teams=" + teams +
                ", totalItem=" + totalItem +
                ", totalPage=" + totalPage +
                '}';
    }
}
