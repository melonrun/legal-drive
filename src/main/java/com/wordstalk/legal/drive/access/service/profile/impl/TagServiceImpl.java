package com.wordstalk.legal.drive.access.service.profile.impl;

import com.wordstalk.legal.drive.access.dao.profile.TagDao;
import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.TagDO;
import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;
import com.wordstalk.legal.drive.access.service.profile.TagService;
import com.wordstalk.legal.drive.access.service.profile.model.TagDTO;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.utils.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    public List<TagDTO> listTags(SearchVO searchVO) throws ServiceException {
        try {
            List<TagDO> tagDOList = tagDao.listTags(new SearchDO(searchVO));
            List<TagDTO> tagDTOList = new ArrayList<>(tagDOList.size());
            tagDOList.forEach(item -> tagDTOList.add(new TagDTO(item)));
            return tagDTOList;
        } catch (Exception e) {
            throw new ServiceException("listTags: fail to listTags.", e);
        }
    }

    @Override
    public long queryTagCount(SearchVO searchVO) throws ServiceException {
        try {
            long count = tagDao.queryTagCount(new SearchDO(searchVO));
            return count;
        } catch (Exception e) {
            throw new ServiceException("queryTagCount: fail to queryCount.", e);
        }
    }

    @Override
    public boolean addTag(TagDTO tagDTO, long ownerId) throws ServiceException {
        try {
            int count = tagDao.addTag(new TagDO(tagDTO, ownerId));
            return count > 0;
        } catch (Exception e) {
            throw new ServiceException("addTag: fail to add Tag.", e);
        }
    }

    @Override
    public boolean updateTag(TagDTO tagDTO) throws ServiceException {
        try {
            int count = tagDao.updateTag(new TagDO(tagDTO));
            return count > 0;
        } catch (Exception e) {
            throw new ServiceException("updateTag: fail to add Tag.", e);
        }
    }

    @Override
    public TagDTO getTagById(String tagId) throws ServiceException {
        try{
            TagDO tagDO = tagDao.getTagById(tagId);
            return new TagDTO(tagDO);
        }catch (Exception e){
            throw new ServiceException("getTagById: fail to get tag.");
        }
    }
}
