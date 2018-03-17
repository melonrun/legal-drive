package com.wordstalk.legal.drive.access.dao.profile.mapper;

import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guoyong on 2018/1/2.
 */
@Repository
public interface TeamMapper {

    List<TeamDO> listTeams(SearchDO searchDO);

    int addTeam(TeamDO teamDO);

    int updateTeam(TeamDO teamDO);

    long queryTeamCount(SearchDO searchDO);

    Long getTeamIdByPath(@Param("teamName") String lastDir);

    int updateFolderName(@Param("folderName") String teamFolderName, @Param("id") long id);

    TeamDO getTeamById(@Param("teamId") String teamId);
}
