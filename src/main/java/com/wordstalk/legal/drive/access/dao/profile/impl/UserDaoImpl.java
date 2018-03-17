package com.wordstalk.legal.drive.access.dao.profile.impl;

import com.wordstalk.legal.drive.access.dao.annotation.UseMaster;
import com.wordstalk.legal.drive.access.dao.profile.UserDao;
import com.wordstalk.legal.drive.access.dao.profile.mapper.UserMapper;
import com.wordstalk.legal.drive.access.dao.profile.model.EntryDO;
import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.UserDO;
import com.wordstalk.legal.drive.access.dao.profile.model.UserShowDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guoyong on 2018/1/2.
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    public UserDaoImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private UserMapper userMapper;

    @Override
    public EntryDO loginByNameAndPass(String userName, String userPass) {
        Map<String, Object> params = new HashMap(2);
        params.put("userName", userName);
        params.put("userPass", userPass);
        return userMapper.loginByNameAndPass(params);
    }

    @UseMaster
    @Override
    public int updateTimeAndIp(long userId, String loginIp) {
        Map<String, Object> params = new HashMap(2);
        params.put("id", userId);
        params.put("loginIp", loginIp);
        return userMapper.updateTimeAndIp(params);
    }

    @Override
    public List<UserShowDO> listUserShowDO(SearchDO searchDO) {
        List<UserShowDO> showDOList = userMapper.listUserShowDO(searchDO);
        return showDOList;
    }

    @UseMaster
    @Override
    public int addUserAndGetId(UserDO userDO) {
        int result = userMapper.addUserAndGetId(userDO);
        return result;
    }

    @Override
    public int addUserJoinRole(long userId, long roleId) {
        int result = userMapper.addUserJoinRole(userId, roleId);
        return result;
    }

    @Override
    public int addUserJoinTeam(long userId, String teamIds) {
        int result = userMapper.addUserJoinTeam(userId, teamIds);
        return result;
    }

    @Override
    public int updateUser(UserDO userDO) {
        int result = userMapper.updateUser(userDO);
        return result;
    }

    @Override
    public int updateUserJoinRole(long userId, long roleId) {
        int result = userMapper.updateUserJoinRole(userId, roleId);
        return result;
    }

    @Override
    public int updateUserJoinTeam(long userId, String teamIds) {
        int result = userMapper.updateUserJoinTeam(userId, teamIds);
        return result;
    }

    @Override
    public long queryUserCount(SearchDO searchDO) {
        long count = userMapper.queryUserCount(searchDO);
        return count;
    }

    @Override
    public String getUserById(String userId) {
        String userName = userMapper.getUserById(userId);
        return userName;
    }
}
