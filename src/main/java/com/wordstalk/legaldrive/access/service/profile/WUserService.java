package com.wordstalk.legaldrive.access.service.profile;


import com.wordstalk.legaldrive.access.service.profile.model.WUserDTO;
import com.wordstalk.legaldrive.utils.exception.ServiceException;

/**
 * Created by y on 2018/1/2.
 */
public interface WUserService {

    WUserDTO loginByNameAndPass(String userName, String userPass, String loginIp) throws ServiceException;
}
