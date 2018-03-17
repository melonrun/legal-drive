package com.wordstalk.legal.drive.controller.profile;

import com.wordstalk.legal.drive.access.service.profile.UserService;
import com.wordstalk.legal.drive.access.service.profile.model.UserShowDTO;
import com.wordstalk.legal.drive.controller.common.AbstractBaseController;
import com.wordstalk.legal.drive.controller.common.MapResult;
import com.wordstalk.legal.drive.controller.common.Result;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.controller.profile.viewo.UserPagerVO;
import com.wordstalk.legal.drive.controller.profile.viewo.UserVO;
import com.wordstalk.legal.drive.inject.annotation.PartnerRequired;
import com.wordstalk.legal.drive.utils.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by guoyong on 2018/1/16.
 */
@Controller
@RequestMapping(value = "/user")
@PartnerRequired
public class UserController extends AbstractBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntryController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object add(@RequestBody @Valid UserVO userVO, BindingResult bindingResult) {
        String errors = CommonUtils.getErrorByBindResult(bindingResult);
        if (!StringUtils.isBlank(errors)) {
            throw new IllegalArgumentException("请确认参数是否填写正确.");
        }
        try {
            boolean result;
            if (userVO.getUserId() <= 0) {
                result = userService.addUser(userVO);
            } else {
                result = userService.updateUser(userVO);
            }

            if (result) {
                return initOkResultNoData();
            }
        } catch (Exception e) {
            LOGGER.error("添加用户失败，", e);
        }
        return initErrorResult("添加用户失败！");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object listUsers(SearchVO searchVO) {
        MapResult dataResult = initMapResult(1);
        try {
            List<UserShowDTO> userDTOList = userService.listUsers(searchVO);
            long userCount = userService.queryUserCount(searchVO);
            if (CollectionUtils.isEmpty(userDTOList) || userCount <= 0) {
                return initErrorResult("没有用户。");
            }

            UserPagerVO userPagerVO = new UserPagerVO();
            userPagerVO.setUsers(userDTOList);
            userPagerVO.setTotalPage((userCount - 1) / searchVO.getPageSize() + 1);
            userPagerVO.setTotalItem(userCount);
            Result<UserPagerVO> result = initOkResult(userPagerVO);
            return result;
        } catch (Exception e) {
            dataResult.putData("status", false);
            LOGGER.error("获取用户列表失败：", e);
        }
        return initErrorResult("fail to get teamList.");
    }
}
