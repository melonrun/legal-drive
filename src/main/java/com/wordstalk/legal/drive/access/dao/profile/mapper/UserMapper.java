package com.wordstalk.legal.drive.access.dao.profile.mapper;

import com.wordstalk.legal.drive.access.dao.profile.model.EntryDO;
import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.UserDO;
import com.wordstalk.legal.drive.access.dao.profile.model.UserShowDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by y on 2018/1/2.
 */
@Repository
public interface UserMapper {
    EntryDO loginByNameAndPass(Map<String, Object> params);

    int updateTimeAndIp(Map<String, Object> params);

    int addUserAndGetId(UserDO userDO);

    int addUserJoinRole(@Param("userId") long userId, @Param("roleId") long roleId);

    int addUserJoinTeam(@Param("userId") long userId, @Param("teamList") String teamList);

    List<UserShowDO> listUserShowDO(SearchDO searchDO);

    int updateUser(UserDO userDO);

    int updateUserJoinRole(@Param("userId") long userId, @Param("roleId") long roleId);

    int updateUserJoinTeam(@Param("userId") long userId, @Param("teamList") String teamIds);

    long queryUserCount(SearchDO searchDO);

    String getUserById(String userId);
}
