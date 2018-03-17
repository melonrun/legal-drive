package com.wordstalk.legal.drive.access.service.profile.impl;

import com.wordstalk.legal.drive.access.dao.profile.RoleDao;
import com.wordstalk.legal.drive.access.dao.profile.model.RoleDO;
import com.wordstalk.legal.drive.access.service.profile.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by guoyong on 2018/1/21.
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;


    @Override
    public List<RoleDO> listRoles() {
        List<RoleDO> roleDOList = roleDao.listRoles();
        return roleDOList;
    }
}
