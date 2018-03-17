package com.wordstalk.legal.drive.access.dao.profile.impl;

import com.wordstalk.legal.drive.access.dao.annotation.UseMaster;
import com.wordstalk.legal.drive.access.dao.profile.TeamDao;
import com.wordstalk.legal.drive.access.dao.profile.mapper.TeamMapper;
import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
@Component
public class TeamDaoImpl implements TeamDao {

    @Autowired
    public TeamDaoImpl(TeamMapper teamMapper) {
        this.teamMapper = teamMapper;
    }

    private TeamMapper teamMapper;

    @Override
    public List<TeamDO> listTeams(SearchDO searchDO) {
        List<TeamDO> teamDOList = teamMapper.listTeams(searchDO);
        return teamDOList;
    }

    @Override
    @UseMaster
    public int addTeam(TeamDO teamDO) {
        int result = teamMapper.addTeam(teamDO);
        return result;
    }

    @Override
    public long queryTeamCount(SearchDO searchDO) {
        long count = teamMapper.queryTeamCount(searchDO);
        return count;
    }

    @Override
    public int updateTeam(TeamDO teamDO) {
        int result = teamMapper.updateTeam(teamDO);
        return result;
    }

    @Override
    public long getTeamIdByPath(String lastDir) {
        Long teamId = teamMapper.getTeamIdByPath(lastDir);
        return teamId == null ? 0 : teamId.longValue();
    }

    @Override
    public int updateFolderName(String teamFolderName, long id) {
        int result = teamMapper.updateFolderName(teamFolderName, id);
        return result;
    }

    @Override
    public TeamDO getTeamById(String teamId) {
        TeamDO teamDO = teamMapper.getTeamById(teamId);
        return teamDO;
    }

}
