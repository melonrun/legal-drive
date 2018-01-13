package com.wordstalk.legaldrive.access.dao.profile;

import com.wordstalk.legaldrive.access.dao.profile.model.WUserDO;

import java.util.Map;

/**
 * Created by y on 2018/1/2.
 */
public interface WUserDao {

    WUserDO loginByNameAndPass(String userName, String userPass);

    int updateTimeAndIp(long userId, String loginIp);
}
