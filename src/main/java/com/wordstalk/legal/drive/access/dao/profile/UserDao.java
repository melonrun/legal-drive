package com.wordstalk.legal.drive.access.dao.profile;

import com.wordstalk.legal.drive.access.dao.profile.model.EntryDO;
import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.UserDO;
import com.wordstalk.legal.drive.access.dao.profile.model.UserShowDO;

import java.util.List;

/**
 * Created by guoyong on 2018/1/2.
 */
public interface UserDao {

    EntryDO loginByNameAndPass(String userName, String userPass);

    int updateTimeAndIp(long userId, String loginIp);

    List<UserShowDO> listUserShowDO(SearchDO searchDO);

    int addUserAndGetId(UserDO userDO);

    int addUserJoinRole(long id, long roleId);

    int addUserJoinTeam(long id, String teamIds);

    int updateUser(UserDO userDO);

    int updateUserJoinRole(long userId, long roleId);

    int updateUserJoinTeam(long userId, String teamIds);

    long queryUserCount(SearchDO searchDO);

    String getUserById(String userId);
}
