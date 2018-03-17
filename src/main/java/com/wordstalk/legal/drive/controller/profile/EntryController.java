package com.wordstalk.legal.drive.controller.profile;

import com.wordstalk.legal.drive.access.service.profile.EntryService;
import com.wordstalk.legal.drive.access.service.profile.model.EntryDTO;
import com.wordstalk.legal.drive.controller.common.AbstractBaseController;
import com.wordstalk.legal.drive.controller.profile.viewo.LoginUserInVO;
import com.wordstalk.legal.drive.controller.profile.viewo.LoginUserOutVO;
import com.wordstalk.legal.drive.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by guoyong on 2017/12/26.
 */
@Controller
@RequestMapping(value = "/entry")
public class EntryController extends AbstractBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntryController.class);

    @Autowired
    private EntryService entryService;

    @RequestMapping(value = "/login", method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object loginByUserPass(@RequestBody @Valid LoginUserInVO userInVO,
                                                  HttpSession session, HttpServletRequest request) {
        try{
            String loginIp = ip(request);
            EntryDTO entryDTO = entryService.loginByNameAndPass(userInVO.getUserName(),
                    userInVO.getUserPass(), loginIp);
            SessionUtils.putSessionAfterLogin(session, entryDTO);
            LoginUserOutVO userOutVO = entryDTO.toVO();
            return initOkResult(userOutVO);
        }catch (Exception e){
            LOGGER.warn("用户登陆失败：{}", userInVO.getUserName(), e);
        }
        return initErrorResult("用户名或者密码错误：" + userInVO.getUserName());
    }
}
