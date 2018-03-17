package com.wordstalk.legal.drive.access.dao.document.impl;

import com.wordstalk.legal.drive.access.dao.document.FileDao;
import com.wordstalk.legal.drive.access.dao.document.mapper.FileMapper;
import com.wordstalk.legal.drive.access.dao.document.model.FileDO;
import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guoyong on 2018/1/28.
 */
@Component
public class FileDaoImpl implements FileDao {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public int insertFile(FileDO fileDO) {
        return fileMapper.insertFile(fileDO);
    }

    @Override
    public List<FileDO> listFilesOneTeam(SearchDO searchDO, String parentId, String teamId) {
        Map<String, Object> params = new HashMap<>();
        params.put("teamId", teamId);
        params.put("start", searchDO.getStart());
        params.put("size", searchDO.getSize());
        params.put("status", searchDO.getStatus());
        params.put("parentId", parentId);
        List<FileDO> fileDOS = fileMapper.listFilesOneTeam(params);
        return fileDOS;
    }

    @Override
    public long countFilesOneTeam(SearchDO searchDO, String parentId, String teamId) {
        Map<String, Object> params = new HashMap<>();
        params.put("teamId", teamId);
        params.put("start", searchDO.getStart());
        params.put("size", searchDO.getSize());
        params.put("status", searchDO.getStatus());
        params.put("parentId", parentId);
        long fileCount = fileMapper.countFilesOneTeam(params);
        return fileCount;
    }

    @Override
    public List<FileDO> listFileMultiTeam(SearchDO searchDO, String parentId, List<String> teamList) {
        Map<String, Object> params = new HashMap<>();
        params.put("teamIds", teamList);
        params.put("start", searchDO.getStart());
        params.put("size", searchDO.getSize());
        params.put("status", searchDO.getStatus());
        params.put("parentId", parentId);
        List<FileDO> fileDOS = fileMapper.listFileMultiTeam(params);
        return fileDOS;
    }

    @Override
    public long countFileMultiTeam(SearchDO searchDO, String parentId, List<String> teamList) {
        Map<String, Object> params = new HashMap<>();
        params.put("teamIds", teamList);
        params.put("start", searchDO.getStart());
        params.put("size", searchDO.getSize());
        params.put("status", searchDO.getStatus());
        params.put("parentId", parentId);
        long fileCount = fileMapper.countFileMultiTeam(params);
        return fileCount;
    }

    @Override
    public String queryPathByTeamId(String teamId) {
        String currPath = fileMapper.queryPathByTeamId(teamId);
        return currPath;
    }

    @Override
    public FileDO getFileById(long parentId) {
        FileDO fileDO = fileMapper.getFileById(parentId);
        return fileDO;
    }
}
