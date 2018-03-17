package com.wordstalk.legal.drive.access.service.document;

import com.wordstalk.legal.drive.access.service.document.model.FileDTO;
import com.wordstalk.legal.drive.access.service.profile.model.EntryDTO;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.utils.exception.ServiceException;

import java.util.List;

/**
 * Created by guoyong on 2018/1/28.
 */
public interface FileService {

    boolean insertFile(FileDTO fileDTO) throws ServiceException;

    List<FileDTO> listFiles(SearchVO searchVO, String parentId, String[] teamList) throws ServiceException;

    long queryFileCount(SearchVO searchVO, String parentId, String[] teamList) throws ServiceException;

    String queryCurrentPath(String[] teamList, long parentId) throws ServiceException;

    FileDTO getFileById(long fileId) throws ServiceException;
}
