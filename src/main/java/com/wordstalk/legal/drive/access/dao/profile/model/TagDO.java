package com.wordstalk.legal.drive.access.dao.profile.model;

import com.wordstalk.legal.drive.access.service.profile.model.TagDTO;

import java.time.LocalDateTime;

/**
 * Created by guoyong on 2018/1/20.
 */
public class TagDO {

    private long id;
    private String tagName;
    private LocalDateTime createTime;
    private long ownerId;

    public TagDO(){

    }

    public TagDO(TagDTO tagDTO){
        this.id = tagDTO.getId();
        this.tagName = tagDTO.getTagName();
    }

    public TagDO(TagDTO tagDTO, long ownerId){
        this.id = tagDTO.getId();
        this.tagName = tagDTO.getTagName();
        this.ownerId = ownerId;
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "TagDO{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
