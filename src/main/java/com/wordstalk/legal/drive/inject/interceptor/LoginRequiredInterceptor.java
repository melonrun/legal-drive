package com.wordstalk.legal.drive.inject.interceptor;

import com.wordstalk.legal.drive.access.service.profile.model.EntryDTO;
import com.wordstalk.legal.drive.controller.common.CommonField;
import com.wordstalk.legal.drive.inject.annotation.LoginRequired;
import com.wordstalk.legal.drive.inject.configuration.ServerConfiguration;
import com.wordstalk.legal.drive.utils.exception.SessionTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by y on 2018/1/16.
 */
@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRequiredInterceptor.class);

    @Autowired
    private ServerConfiguration serverConfiguration;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        /* 只会拦注解类的方法 */
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class clazz = handlerMethod.getBeanType();

        if(clazz.isAnnotationPresent(LoginRequired.class)){
            EntryDTO entryDTO= (EntryDTO) request.getSession().getAttribute(CommonField.SESSION_ASSERTION_LD);
            if(entryDTO == null){
                throw new SessionTimeoutException("session time out, please login!");
            }
            String loginName = entryDTO.getUserName();
            LOGGER.info("用户{} 进入系统", loginName);
            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
