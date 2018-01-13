package com.wordstalk.legaldrive.access.service.profile.impl;

import com.wordstalk.legaldrive.access.dao.profile.WUserDao;
import com.wordstalk.legaldrive.access.dao.profile.model.WUserDO;
import com.wordstalk.legaldrive.access.service.profile.WUserService;
import com.wordstalk.legaldrive.access.service.profile.model.WUserDTO;
import com.wordstalk.legaldrive.utils.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by y on 2018/1/2.
 */
@Service
public class WUserServiceImpl implements WUserService {

    @Autowired
    private WUserDao wUserDao;

    @Transactional
    @Override
    public WUserDTO loginByNameAndPass(String userName, String userPass, String loginIp) throws ServiceException {
        try {
            WUserDO wUserDO = this.wUserDao.loginByNameAndPass(userName, userPass);
            if (wUserDO != null){
                WUserDTO wUserDTO = new WUserDTO();
                BeanUtils.copyProperties(wUserDO, wUserDTO);
                wUserDao.updateTimeAndIp(wUserDO.getId(), loginIp);
                return wUserDTO;
            }
            throw new ServiceException("用户名或者密码错误：" + userName);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
