package com.wordstalk.legal.drive.controller.profile;

import com.wordstalk.legal.drive.access.dao.profile.model.RoleDO;
import com.wordstalk.legal.drive.access.service.profile.RoleService;
import com.wordstalk.legal.drive.access.service.profile.model.TeamDTO;
import com.wordstalk.legal.drive.controller.common.AbstractBaseController;
import com.wordstalk.legal.drive.controller.common.Result;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.inject.annotation.PartnerRequired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by guoyong on 2018/1/21.
 */
@Controller
@RequestMapping(value = "/role")
@PartnerRequired
public class RoleController extends AbstractBaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object listRoles() {
        try {
            List<RoleDO> roleDOList= roleService.listRoles();
            Result<List<RoleDO>> result = initOkResult(roleDOList);
            return result;
        } catch (Exception e) {
            LOGGER.error("listTeam fail: ", e);
        }
        return initErrorResult("fail to search teamList.");
    }


}
