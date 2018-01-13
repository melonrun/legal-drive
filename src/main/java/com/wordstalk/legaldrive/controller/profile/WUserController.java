package com.wordstalk.legaldrive.controller.profile;

import com.wordstalk.legaldrive.access.service.profile.WUserService;
import com.wordstalk.legaldrive.access.service.profile.model.WUserDTO;
import com.wordstalk.legaldrive.controller.common.AbstractBaseController;
import com.wordstalk.legaldrive.controller.common.Result;
import com.wordstalk.legaldrive.controller.viewo.WUserVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by y on 2017/12/26.
 */
@Controller
@RequestMapping(value = "/user")
public class WUserController extends AbstractBaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(WUserController.class);

    @Autowired
    private WUserService wUserService;

    @RequestMapping(value = "/login", method= RequestMethod.GET)
    @ResponseBody
    public Result<WUserVO> loginByUserPass(@Valid WUserVO wUserVO, HttpServletRequest request) {
        Result<WUserVO> result = initResult();
        try{
            String loginIp = ip(request);
            WUserDTO wUserDTO = wUserService.loginByNameAndPass(wUserVO.getUserName(), wUserVO.getUserPass(), loginIp);
            BeanUtils.copyProperties(wUserDTO, wUserVO);
            result.setResult(wUserVO);
            result.setStatus(true);
            return result;
        }catch (Exception e){
            result.setStatus(false);
            result.setMsg("用户名或者密码错误：" + wUserVO.getUserName());
            LOGGER.warn("用户登陆失败：{}", wUserVO.getUserName(),e);
        }
        return result;
    }

}
