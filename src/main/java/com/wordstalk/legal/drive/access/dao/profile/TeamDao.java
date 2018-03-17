package com.wordstalk.legal.drive.access.dao.profile;

import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;

import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
public interface TeamDao {

    List<TeamDO> listTeams(SearchDO searchDO);

    int addTeam(TeamDO teamDTO);

    long queryTeamCount(SearchDO searchDO);

    int updateTeam(TeamDO teamDO);

    long getTeamIdByPath(String lastDir);

    int updateFolderName(String teamFolderName, long id);

    TeamDO getTeamById(String teamId);
}
