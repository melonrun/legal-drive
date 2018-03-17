package com.wordstalk.legal.drive.access.service.profile.impl;

import com.wordstalk.legal.drive.access.dao.document.FileDao;
import com.wordstalk.legal.drive.access.dao.document.model.FileDO;
import com.wordstalk.legal.drive.access.dao.profile.TeamDao;
import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;
import com.wordstalk.legal.drive.access.service.profile.TeamService;
import com.wordstalk.legal.drive.access.service.profile.model.TeamDTO;
import com.wordstalk.legal.drive.controller.common.CommonField;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.utils.CommonUtils;
import com.wordstalk.legal.drive.utils.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private FileDao fileDao;

    @Override
    public List<TeamDTO> listTeams(SearchVO searchVO) throws ServiceException {
        try {
            List<TeamDO> teamDOList = teamDao.listTeams(new SearchDO(searchVO));
            List<TeamDTO> teamDTOList = new ArrayList<>(teamDOList.size());
            teamDOList.forEach(item -> teamDTOList.add(new TeamDTO(item)));
            return teamDTOList;
        } catch (Exception e) {
            throw new ServiceException("listTeams: fail to listTeams.", e);
        }
    }

    @Override
    public TeamDTO getTeamById(String teamId) throws ServiceException {
        try{
            TeamDO teamDO = teamDao.getTeamById(teamId);
            return new TeamDTO(teamDO);
        }catch(Exception e){
            throw new ServiceException("getTeamById: fail", e);
        }
    }

    @Override
    public long queryTeamCount(SearchVO searchVO) throws ServiceException {
        try {
            long count = teamDao.queryTeamCount(new SearchDO(searchVO));
            return count;
        } catch (Exception e) {
            throw new ServiceException("queryTeamCount: fail to queryCount.", e);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean addTeam(TeamDTO teamDTO, long ownerId) throws ServiceException {
        try {
            TeamDO teamDO = teamDTO.toDO(ownerId);
            int addResult = teamDao.addTeam(teamDO);
            String folderName = CommonUtils.getTeamFolderName(teamDO);
            int updateResult = teamDao.updateFolderName(CommonUtils.getTeamFolderName(teamDO), teamDO.getId());
            int mkDirResult = fileDao.insertFile(new FileDO(teamDO, folderName));
            if (addResult <= 0 || updateResult <= 0 || mkDirResult <= 0) {
                throw new ServiceException("addTeam: fail to add Team.");
            }
            return true;
        } catch (Exception e) {
            throw new ServiceException("addTeam: fail to add Team.", e);
        }
    }

    @Override
    public boolean updateTeam(TeamDTO teamDTO) throws ServiceException {
        try {
            int count = teamDao.updateTeam(teamDTO.toDO(0));
            return count > 0;
        } catch (Exception e) {
            throw new ServiceException("addTeam: fail to add Team.", e);
        }
    }
}
