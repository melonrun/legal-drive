package com.wordstalk.legaldrive.access.dao.profile.impl;

import com.wordstalk.legaldrive.access.dao.annotation.UseMaster;
import com.wordstalk.legaldrive.access.dao.profile.WUserDao;
import com.wordstalk.legaldrive.access.dao.profile.mapper.WUserMapper;
import com.wordstalk.legaldrive.access.dao.profile.model.WUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by y on 2018/1/2.
 */
@Component
public class WUserDaoImpl implements WUserDao {

    @Autowired
    public WUserDaoImpl(WUserMapper wUserMapper) {
        this.wUserMapper = wUserMapper;
    }

    private WUserMapper wUserMapper;

    @UseMaster
    @Override
    public WUserDO loginByNameAndPass(String userName, String userPass) {
        Map<String, Object> params = new HashMap(2);
        params.put("userName", userName);
        params.put("userPass", userPass);
        return wUserMapper.loginByNameAndPass(params);
    }

    @UseMaster
    @Override
    public int updateTimeAndIp(long userId, String loginIp) {
        Map<String, Object> params = new HashMap(2);
        params.put("id", userId);
        params.put("loginIp", loginIp);
        return wUserMapper.updateTimeAndIp(params);
    }
}
