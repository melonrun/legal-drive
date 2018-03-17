package com.wordstalk.legal.drive.access.dao.profile.impl;

import com.wordstalk.legal.drive.access.dao.profile.RoleDao;
import com.wordstalk.legal.drive.access.dao.profile.mapper.RoleMapper;
import com.wordstalk.legal.drive.access.dao.profile.model.RoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by guoyong on 2018/1/21.
 */
@Component
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDO> listRoles() {
        List<RoleDO> roleDOList = this.roleMapper.listRoles();
        return roleDOList;
    }
}
