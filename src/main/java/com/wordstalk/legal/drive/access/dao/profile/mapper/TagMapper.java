package com.wordstalk.legal.drive.access.dao.profile.mapper;

import com.wordstalk.legal.drive.access.dao.profile.model.SearchDO;
import com.wordstalk.legal.drive.access.dao.profile.model.TagDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
@Repository
public interface TagMapper {

    List<TagDO> listTags(SearchDO searchDO);

    int addTag(TagDO tagDO);

    long queryTagCount(SearchDO searchDO);

    int updateTag(TagDO tagDO);

    TagDO getTagById(@Param("tagId") String tagId);
}
