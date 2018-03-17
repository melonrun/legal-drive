package com.wordstalk.legal.drive.controller.profile.viewo;

import com.wordstalk.legal.drive.access.service.profile.model.TagDTO;

import java.util.List;

/**
 * Created by guoyong on 2018/1/20.
 */
public class TagPagerVO {

    private List<TagDTO> tags;
    private long totalItem;
    private long totalPage;

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

    public long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(long totalItem) {
        this.totalItem = totalItem;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    @Override
    public String toString() {
        return "TagPagerVO{" +
                "tags=" + tags +
                ", totalItem=" + totalItem +
                ", totalPage=" + totalPage +
                '}';
    }
}
