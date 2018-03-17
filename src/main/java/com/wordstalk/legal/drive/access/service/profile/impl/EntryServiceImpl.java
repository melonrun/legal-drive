package com.wordstalk.legal.drive.access.service.profile.impl;

import com.wordstalk.legal.drive.access.dao.profile.UserDao;
import com.wordstalk.legal.drive.access.dao.profile.model.EntryDO;
import com.wordstalk.legal.drive.access.service.profile.EntryService;
import com.wordstalk.legal.drive.access.service.profile.model.EntryDTO;
import com.wordstalk.legal.drive.utils.exception.ServiceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by y on 2018/1/2.
 */
@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public EntryDTO loginByNameAndPass(String userName, String userPass, String loginIp) throws ServiceException {
        try {
            EntryDO entryDO = this.userDao.loginByNameAndPass(userName, userPass);
            if (entryDO != null){
                EntryDTO entryDTO = new EntryDTO(entryDO);
                userDao.updateTimeAndIp(entryDO.getId(), loginIp);
                return entryDTO;
            }
            throw new ServiceException("用户名或者密码错误：" + userName);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
