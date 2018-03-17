package com.wordstalk.legal.drive.access.dao.profile;

import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.TagDO;

import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
public interface TagDao {

    List<TagDO> listTags(SearchDO searchDO);

    int addTag(TagDO tagDO);

    long queryTagCount(SearchDO searchDO);

    int updateTag(TagDO tagDO);

    TagDO getTagById(String tagId);
}
