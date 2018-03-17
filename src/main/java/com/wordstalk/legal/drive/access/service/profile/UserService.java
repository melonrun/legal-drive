package com.wordstalk.legal.drive.access.service.profile;

import com.wordstalk.legal.drive.access.service.profile.model.UserShowDTO;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.controller.profile.viewo.UserVO;
import com.wordstalk.legal.drive.utils.exception.ServiceException;

import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
public interface UserService {

    List<UserShowDTO> listUsers(SearchVO searchVO) throws ServiceException;

    boolean addUser(UserVO userVO) throws ServiceException;

    boolean updateUser(UserVO userVO) throws ServiceException;

    long queryUserCount(SearchVO searchVO) throws ServiceException;

    String getUserById(String userId) throws ServiceException;
}
