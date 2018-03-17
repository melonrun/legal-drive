package com.wordstalk.legal.drive.access.service.profile.model;

import com.wordstalk.legal.drive.access.dao.profile.model.TagDO;

import java.time.format.DateTimeFormatter;

/**
 * Created by guoyong on 2018/1/20.
 */
public class TagDTO {

    private long id;
    private String tagName;
    private String createTime;

    public TagDTO(){

    }

    public TagDTO(TagDO tagDO){
        this.id = tagDO.getId();
        this.tagName = tagDO.getTagName();
        this.createTime = tagDO.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
