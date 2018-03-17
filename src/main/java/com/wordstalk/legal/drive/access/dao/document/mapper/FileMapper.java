package com.wordstalk.legal.drive.access.dao.document.mapper;

import com.wordstalk.legal.drive.access.dao.document.model.FileDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by guoyong on 2018/1/28.
 */
public interface FileMapper {

    int insertFile(FileDO fileDO);

    String queryPathByTeamId(@Param("teamId") String teamId);

    List<FileDO> listFilesOneTeam(Map<String, Object> params);

    long countFilesOneTeam(Map<String, Object> params);

    List<FileDO> listFileMultiTeam(Map<String, Object> params);

    long countFileMultiTeam(Map<String, Object> params);

    FileDO getFileById(long parentId);
}
