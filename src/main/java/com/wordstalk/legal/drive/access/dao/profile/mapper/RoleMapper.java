package com.wordstalk.legal.drive.access.dao.profile.mapper;

import com.wordstalk.legal.drive.access.dao.profile.model.RoleDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    List<RoleDO> listRoles();
}
