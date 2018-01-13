package com.wordstalk.legaldrive.access.dao.profile.mapper;

import com.wordstalk.legaldrive.access.dao.profile.model.WUserDO;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by y on 2018/1/2.
 */
@Repository
public interface WUserMapper {
    WUserDO loginByNameAndPass(Map<String, Object> params);

    int updateTimeAndIp(Map<String, Object> params);
}
