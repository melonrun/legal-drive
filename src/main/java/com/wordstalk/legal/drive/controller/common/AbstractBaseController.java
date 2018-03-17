package com.wordstalk.legal.drive.controller.common;

import com.wordstalk.legal.drive.access.service.profile.TeamService;
import com.wordstalk.legal.drive.access.service.profile.model.EntryDTO;
import com.wordstalk.legal.drive.inject.configuration.ServerConfiguration;
import com.wordstalk.legal.drive.utils.exception.SessionTimeoutException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by y on 2018/1/7.
 */
public class AbstractBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseController.class);

    @Autowired
    protected ServerConfiguration serverConfiguration;

    @Autowired
    private TeamService teamService;


    protected EntryDTO currentUser(HttpServletRequest request) {
        EntryDTO entryDTO=
                (EntryDTO) request.getSession().getAttribute(CommonField.SESSION_ASSERTION_LD);
        if (entryDTO != null) {
            return entryDTO;
        }
        return new EntryDTO("unKnown");
    }

    protected String getViewPath(HttpServletRequest request, String fePath) {
        try {
            EntryDTO entryDTO=
                    (EntryDTO) request.getSession().getAttribute(CommonField.SESSION_ASSERTION_LD);
            if(StringUtils.isEmpty(fePath)){
                throw new IllegalArgumentException("illegal path.");
            }
            if(fePath.equals("/root") && !CommonField.ROLE_PARTNER_DISPLAY.equals(entryDTO.getRoleNameDisplay())){
                throw new IllegalArgumentException("illegal path.");
            }
            return fePath;
        } catch (Exception e) {
            throw new IllegalArgumentException("fail upload.");
        }
    }

    protected String getRealPath(String viewPath){
        String filePrefix = serverConfiguration.getFilePrefix();
        String realPath = filePrefix + File.separator + viewPath;
        return realPath;
    }

    protected String ip(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0];
        }

        if (!checkIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (!checkIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (!checkIp(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (!checkIp(ip)) {
            ip = request.getRemoteAddr();
        }
        LOGGER.info("ip地址-------------------------------------------------------", ip);
        LOGGER.info("1:" + request.getHeader("Proxy-Client-IP"));
        LOGGER.info("2:" + request.getHeader("WL-Proxy-Client-IP"));
        LOGGER.info("3:" + request.getHeader("X-Real-IP"));
        return ip;

    }

    private static boolean checkIp(String ip) {
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)) {
            return false;
        }
        return true;
    }

    protected <T> Result<T> initOkResult(T data) {
        Result<T> result = new Result<>(new BaseResult.Head(serverConfiguration));
        result.setStatus(true);
        result.setResult(data);
        result.setMsg("[^_^]");
        return result;
    }

    protected <T> Result<T> initOkResultNoData() {
        Result<T> result = new Result<>(new BaseResult.Head(serverConfiguration));
        result.setStatus(true);
        result.setMsg("[^_^]");
        return result;
    }

    protected MapResult initErrorResult(String msg) {
        MapResult result = initMapResult();
        result.putData("status", false);
        result.putData("msg", msg);
        return result;
    }

    protected MapResult initMapResult() {
        MapResult result = new MapResult(new BaseResult.Head(serverConfiguration));
        return result;
    }

    protected MapResult initMapResult(int size) {
        MapResult result = new MapResult(new BaseResult.Head(serverConfiguration), size);
        return result;
    }

    @ExceptionHandler
    @ResponseBody
    public Object handleAndReturnData(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Exception ex) {
        BaseResult.Head header = new BaseResult.Head(serverConfiguration);
        BaseResult result = new BaseResult();
        result.setHead(header);

        if (ex instanceof IllegalArgumentException) {
            LOGGER.warn("Handler ServerErrorException. [{}]", ex);
            result.setHead(badRequest400(ex.getMessage()));
        } else if (ex instanceof UnsupportedOperationException) {
            LOGGER.warn("Handler UnsupportedOperationException. [{}]", ex);
            result.setHead(forbidden403(ex.getMessage()));
        } else if (ex instanceof HttpMessageNotReadableException) {
            LOGGER.warn("Handler HttpMessageNotReadableException");
            result.setHead(badRequest404(ex.getMessage()));
        } else if (ex instanceof SessionTimeoutException) {
            LOGGER.warn("Handler SessionTimeoutException");
            result.setHead(forbidden403(ex.getMessage()));
        } else {
            LOGGER.warn("Handler other Exception. [{}]", ex);
            result.setHead(serverError500("Internal error"));
        }
        return result;
    }


    // 状态方法------------------------------------------------------------------------------------
    protected BaseResult.Head forbidden403(String message) {
        return errorStatus(StatusCodeEnum.CLIENT_ORFORBIDDEN, message);
    }

    protected BaseResult.Head badRequest400(String message) {
        return errorStatus(StatusCodeEnum.CLIENT_ERROR_BAD_REQUEST, message);
    }

    protected BaseResult.Head serverError500(String message) {
        return errorStatus(StatusCodeEnum.SERVER_ERROR_INTERNAL, message);
    }

    protected BaseResult.Head badRequest404(String message) {
        return errorStatus(StatusCodeEnum.SERCER_NOT_RESPONSE, message);
    }

    private BaseResult.Head errorStatus(StatusCodeEnum status, String message) {
        BaseResult.Head header = new BaseResult.Head(serverConfiguration);
        header.setCode(status.getValue());
        header.setMsg(message);
        return header;
    }

}
