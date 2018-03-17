package com.wordstalk.legal.drive.controller.profile;

import com.wordstalk.legal.drive.access.service.profile.TagService;
import com.wordstalk.legal.drive.access.service.profile.model.TagDTO;
import com.wordstalk.legal.drive.controller.common.AbstractBaseController;
import com.wordstalk.legal.drive.controller.common.Result;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.controller.profile.viewo.TagPagerVO;
import com.wordstalk.legal.drive.inject.annotation.PartnerRequired;
import com.wordstalk.legal.drive.utils.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
@Controller
@RequestMapping(value = "/tag")
@PartnerRequired
public class TagController extends AbstractBaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object listTeams(SearchVO searchVO) {
        try {
            List<TagDTO> tagDTOList = tagService.listTags(searchVO);
            long tagCount = tagService.queryTagCount(searchVO);

            TagPagerVO tagPagerVO = new TagPagerVO();
            tagPagerVO.setTags(tagDTOList);
            tagPagerVO.setTotalPage((tagCount - 1) / searchVO.getPageSize() + 1);
            tagPagerVO.setTotalItem(tagCount);
            Result<TagPagerVO> result = initOkResult(tagPagerVO);
            return result;
        } catch (Exception e) {
            LOGGER.error("listTeam fail: ", e);
        }
        return initErrorResult("fail to search teamList.");
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object addTag(@RequestBody @Valid TagDTO tagDTO, BindingResult bindingResult,
                         HttpServletRequest request) {
        String errors = CommonUtils.getErrorByBindResult(bindingResult);
        if (!StringUtils.isBlank(errors)) {
            throw new IllegalArgumentException("请确认参数是否填写正确.");
        }
        try {
            boolean result;
            if (tagDTO.getId() <= 0) {
                result = tagService.addTag(tagDTO, currentUser(request).getId());
            } else {
                result = tagService.updateTag(tagDTO);
            }

            if (result) {
                return initOkResultNoData();
            }
        } catch (Exception e) {
            LOGGER.error("addTag fail: ", e);
        }
        return initErrorResult("fail to addTag.");
    }


}
