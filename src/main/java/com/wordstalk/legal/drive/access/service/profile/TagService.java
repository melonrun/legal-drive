package com.wordstalk.legal.drive.access.service.profile;

import com.wordstalk.legal.drive.access.service.profile.model.TagDTO;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.utils.exception.ServiceException;

import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
public interface TagService {

    List<TagDTO> listTags(SearchVO searchVO) throws ServiceException;

    boolean addTag(TagDTO tagDTO, long ownerId) throws ServiceException;

    long queryTagCount(SearchVO searchVO) throws ServiceException;

    boolean updateTag(TagDTO tagDTO) throws ServiceException;

    TagDTO getTagById(String tagId) throws ServiceException;
}
