package com.wordstalk.legal.drive.access.service.profile.impl;

import com.wordstalk.legal.drive.access.dao.profile.TeamDao;
import com.wordstalk.legal.drive.access.dao.profile.UserDao;
import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;
import com.wordstalk.legal.drive.access.dao.profile.model.UserDO;
import com.wordstalk.legal.drive.access.dao.profile.model.UserShowDO;
import com.wordstalk.legal.drive.access.service.profile.UserService;
import com.wordstalk.legal.drive.access.service.profile.model.UserShowDTO;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.controller.profile.viewo.UserVO;
import com.wordstalk.legal.drive.utils.CommonUtils;
import com.wordstalk.legal.drive.utils.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by guoyong on 2018/1/20.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TeamDao teamDao;

    @Override
    public List<UserShowDTO> listUsers(SearchVO searchVO) throws ServiceException {
        try {
            List<UserShowDO> userDOList = this.userDao.listUserShowDO(new SearchDO(searchVO));
            List<UserShowDTO> userDTOList = new ArrayList<>(userDOList.size());
            List<TeamDO> teamDOList = teamDao.listTeams(new SearchDO());
            Map<Long, TeamDO> teamDOMap = CommonUtils.getTeamMapByList(teamDOList);

            userDOList.forEach(item -> userDTOList.add(new UserShowDTO(item, teamDOMap)));

            return userDTOList;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean addUser(UserVO userVO) throws ServiceException {
        try {
            UserDO userDO = userVO.toUserDO();
            int addResult = userDao.addUserAndGetId(userDO);
            int roleResult = userDao.addUserJoinRole(userDO.getId(), userVO.getRoleId());
            int teamResult = userDao.addUserJoinTeam(userDO.getId(),
                    StringUtils.join(userVO.getTeamIds(), ","));
            if (addResult <= 0 || roleResult <= 0 || teamResult <= 0) {
                throw new ServiceException("添加用户失败.");
            }
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public boolean updateUser(UserVO userVO) throws ServiceException {
        try {
            UserDO userDO = userVO.toUserDO();
            int addResult = userDao.updateUser(userDO);
            int roleResult = userDao.updateUserJoinRole(userDO.getId(), userVO.getRoleId());
            if(roleResult == 0){
                roleResult = userDao.addUserJoinRole(userDO.getId(), userVO.getRoleId());
            }
            int teamResult = userDao.updateUserJoinTeam(userDO.getId(),
                    StringUtils.join(userVO.getTeamIds(), ","));
            if(teamResult == 0){
                userDao.addUserJoinTeam(userDO.getId(),
                        StringUtils.join(userVO.getTeamIds(), ","));
            }
            if (addResult <= 0 || roleResult <= 0 || teamResult <= 0) {
                throw new ServiceException("更新用户失败.");
            }
            return true;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long queryUserCount(SearchVO searchVO) throws ServiceException {
        try {
            long count = userDao.queryUserCount(new SearchDO(searchVO));
            return count;
        } catch (Exception e) {
            throw new ServiceException("queryUserCount: fail to queryCount.", e);
        }
    }

    @Override
    public String getUserById(String userId) throws ServiceException {
        try{
            String userName = userDao.getUserById(userId);
            return userName;
        } catch (Exception e){
            throw new ServiceException("getUserById: fail to getUserById");
        }
    }
}
