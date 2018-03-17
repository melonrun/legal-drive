package com.wordstalk.legal.drive.access.service.profile;

import com.wordstalk.legal.drive.access.service.profile.model.TeamDTO;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.utils.exception.ServiceException;

import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
public interface TeamService {

    List<TeamDTO> listTeams(SearchVO searchVO) throws ServiceException;

    TeamDTO getTeamById(String teamId) throws ServiceException;

    boolean addTeam(TeamDTO teamDTO, long ownerId) throws ServiceException;

    long queryTeamCount(SearchVO searchVO) throws ServiceException;

    boolean updateTeam(TeamDTO teamDTO) throws ServiceException;
}




