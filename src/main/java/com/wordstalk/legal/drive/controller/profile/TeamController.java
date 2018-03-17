package com.wordstalk.legal.drive.controller.profile;

import com.wordstalk.legal.drive.access.service.profile.TeamService;
import com.wordstalk.legal.drive.access.service.profile.model.TeamDTO;
import com.wordstalk.legal.drive.controller.common.AbstractBaseController;
import com.wordstalk.legal.drive.controller.common.CommonField;
import com.wordstalk.legal.drive.controller.common.Result;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.controller.profile.viewo.TeamPagerVO;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
@Controller
@RequestMapping(value = "/team")
@PartnerRequired
public class TeamController extends AbstractBaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object listTeams(SearchVO searchVO, HttpServletRequest request) {
        try {
            List<TeamDTO> teamDTOList = new ArrayList<>();
            if (CommonField.ROLE_PARTNER_DISPLAY.equals(currentUser(request).getRoleNameDisplay())) {
                teamDTOList = teamService.listTeams(searchVO);
            } else {
                String[] tIds = currentUser(request).getTeamList().split(",");
                for (String tid : tIds) {
                    teamDTOList.add(teamService.getTeamById(tid));
                }
            }
            long teamCount = teamService.queryTeamCount(searchVO);

            TeamPagerVO teamPagerVO = new TeamPagerVO();
            teamPagerVO.setTeams(teamDTOList);
            teamPagerVO.setTotalPage((teamCount - 1) / searchVO.getPageSize() + 1);
            teamPagerVO.setTotalItem(teamCount);
            Result<TeamPagerVO> result = initOkResult(teamPagerVO);
            return result;
        } catch (Exception e) {
            LOGGER.error("listTeam fail: ", e);
        }
        return initErrorResult("fail to search teamList.");
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object addTeam(@RequestBody @Valid TeamDTO teamDTO, BindingResult bindingResult,
                          HttpServletRequest request) {
        String errors = CommonUtils.getErrorByBindResult(bindingResult);
        if (!StringUtils.isBlank(errors)) {
            throw new IllegalArgumentException("请确认参数是否填写正确.");
        }
        try {
            boolean result;
            if (teamDTO.getId() <= 0) {
                result = teamService.addTeam(teamDTO, currentUser(request).getId());
            } else {
                result = teamService.updateTeam(teamDTO);
            }

            if (result) {
                return initOkResultNoData();
            }
        } catch (Exception e) {
            LOGGER.error("addTeam fail: ", e);
        }
        return initErrorResult("添加团队失败！");
    }
}
