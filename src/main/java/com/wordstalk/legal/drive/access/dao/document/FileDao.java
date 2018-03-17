package com.wordstalk.legal.drive.access.dao.document;

import com.wordstalk.legal.drive.access.dao.document.model.FileDO;
import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.service.document.model.FileDTO;

import java.util.List;

/**
 * Created by guoyong on 2018/1/28.
 */
public interface FileDao {

    int insertFile(FileDO fileDO);

    List<FileDO> listFilesOneTeam(SearchDO searchDO, String parentId, String teamId);

    List<FileDO> listFileMultiTeam(SearchDO searchDO, String parentId, List<String> teamList);

    long countFilesOneTeam(SearchDO searchDO, String parentId, String teamId);

    long countFileMultiTeam(SearchDO searchDO, String parentId, List<String> teamList);

    String queryPathByTeamId(String teamId);

    FileDO getFileById(long parentId);
}
