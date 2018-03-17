package com.wordstalk.legal.drive.access.dao.profile.impl;

import com.wordstalk.legal.drive.access.dao.annotation.UseMaster;
import com.wordstalk.legal.drive.access.dao.profile.TagDao;
import com.wordstalk.legal.drive.access.dao.profile.mapper.TagMapper;
import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.TagDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
@Component
public class TagDaoImpl implements TagDao {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagDO> listTags(SearchDO searchDO) {
        List<TagDO> tagDOList = tagMapper.listTags(searchDO);
        return tagDOList;
    }

    @Override
    @UseMaster
    public int addTag(TagDO tagDO) {
        int result = tagMapper.addTag(tagDO);
        return result;
    }

    @Override
    public long queryTagCount(SearchDO searchDO) {
        long count = tagMapper.queryTagCount(searchDO);
        return count;
    }

    @Override
    public int updateTag(TagDO tagDO) {
        int result = tagMapper.updateTag(tagDO);
        return result;
    }

    @Override
    public TagDO getTagById(String tagId) {
        TagDO tagDO = tagMapper.getTagById(tagId);
        return tagDO;
    }
}
