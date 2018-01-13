package com.wordstalk.legaldrive.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by y on 2018/1/7.
 */
@Controller
@RequestMapping("/h")
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    //todo 后续还需要根据跟前端的讨论进行修改
    @RequestMapping(value = "/**", method = RequestMethod.GET)
    public String index(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        return "index";
    }
}
