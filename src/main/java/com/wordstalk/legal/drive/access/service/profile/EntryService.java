package com.wordstalk.legal.drive.access.service.profile;


import com.wordstalk.legal.drive.access.service.profile.model.EntryDTO;
import com.wordstalk.legal.drive.utils.exception.ServiceException;

/**
 * Created by y on 2018/1/2.
 */
public interface EntryService {

    EntryDTO loginByNameAndPass(String userName, String userPass, String loginIp) throws ServiceException;
}
