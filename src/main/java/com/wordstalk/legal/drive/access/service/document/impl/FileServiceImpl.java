package com.wordstalk.legal.drive.access.service.document.impl;

import com.wordstalk.legal.drive.access.dao.document.FileDao;
import com.wordstalk.legal.drive.access.dao.document.model.FileDO;
import com.wordstalk.legal.drive.access.dao.profile.TeamDao;
import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;
import com.wordstalk.legal.drive.access.service.document.FileService;
import com.wordstalk.legal.drive.access.service.document.model.FileDTO;
import com.wordstalk.legal.drive.access.service.profile.model.EntryDTO;
import com.wordstalk.legal.drive.controller.common.CommonField;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.utils.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by guoyong on 2018/1/28.
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Autowired
    private TeamDao teamDao;

    @Override
    public boolean insertFile(FileDTO fileDTO) throws ServiceException {
        try {
            FileDO fileDO = fileDTO.toDO();
            int result = fileDao.insertFile(fileDO);
            return result > 0;
        } catch (Exception e) {
            throw new ServiceException("listTags: fail to listTags.", e);
        }
    }

    @Override
    public List<FileDTO> listFiles(SearchVO searchVO, String parentId, String[] teamList) throws ServiceException {
        try {
            List<FileDO> fileDOList;
            if (teamList.length == 1) {
                //合伙人可以访问所有文件
                if ("-1".equals(teamList[0])) {
                    List<TeamDO> teamDOList = teamDao.listTeams(new SearchDO());
                    List<String> teamListStr = new ArrayList<>();
                    for (TeamDO teamDO : teamDOList) {
                        teamListStr.add(teamDO.getId() + "");
                    }
                    fileDOList = fileDao.listFileMultiTeam(new SearchDO(searchVO), parentId, teamListStr);
                } else {
                    fileDOList = fileDao.listFilesOneTeam(new SearchDO(searchVO), parentId, teamList[0]);
                }
            } else {
                fileDOList = fileDao.listFileMultiTeam(new SearchDO(searchVO), parentId, Arrays.asList(teamList));
            }
            List<FileDTO> dtoList = new ArrayList<>();
            fileDOList.forEach(item -> {
                FileDTO fileDTO = new FileDTO(item);
                dtoList.add(fileDTO);
            });
            return dtoList;
        } catch (Exception e) {
            throw new ServiceException("listFiles fail.", e);
        }
    }

    @Override
    public long queryFileCount(SearchVO searchVO, String parentId, String[] teamList) throws ServiceException {
        try {
            if (teamList.length == 1) {
                if ("-1".equals(teamList[0])) {
                    List<TeamDO> teamDOList = teamDao.listTeams(new SearchDO());
                    List<String> teamListStr = new ArrayList<>();
                    for (TeamDO teamDO : teamDOList) {
                        teamListStr.add(teamDO.getId() + "");
                    }
                    return fileDao.countFileMultiTeam(new SearchDO(searchVO), parentId, teamListStr);
                } else {
                    return fileDao.countFilesOneTeam(new SearchDO(searchVO), parentId, teamList[0]);
                }
            } else if (teamList.length > 1) {
                return fileDao.countFileMultiTeam(new SearchDO(searchVO), parentId, Arrays.asList(teamList));
            }
            throw new ServiceException("get teamList fail.");
        } catch (Exception e) {
            throw new ServiceException("queryFileCount fail.", e);
        }
    }

    @Override
    public String queryCurrentPath(String[] teamList, long parentId) throws ServiceException {
        try {
            if(parentId == 1){
                if (teamList.length > 1 || (teamList.length == 1 && "-1".equals(teamList[0]))) {
                    return CommonField.DEFAULT_ROOT_PATH;
                }
                String currPath = fileDao.queryPathByTeamId(teamList[0]);
                if (StringUtils.isEmpty(currPath)) {
                    throw new ServiceException("queryPath fail.");
                }
                return currPath;
            } else {
                return fileDao.getFileById(parentId).getFilePath();
            }
        } catch (Exception e) {
            throw new ServiceException("queryPath fail.", e);
        }
    }

    @Override
    public FileDTO getFileById(long fileId) throws ServiceException {
        try{
            FileDO fileDO = fileDao.getFileById(fileId);
            if(fileDO.getFileType() == CommonField.NORMAL_FILE && StringUtils.isNotBlank(fileDO.getFilePath())){
                return new FileDTO(fileDO);
            }
            throw new ServiceException("file type error.");
        }catch(Exception e){
            throw new ServiceException("file type error.", e);
        }
    }


}
