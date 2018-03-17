package com.wordstalk.legal.drive.lucene.model;


import javax.validation.constraints.Min;
import java.time.LocalDateTime;

/**
 * Created by guoyong on 2018/2/4.
 */
public class LuceneSearchDTO {
    @Min(value = 1)
    private long start;
    private long size = 10;
    private String searchKeyWords;
    private String tag;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getSearchKeyWords() {
        return searchKeyWords;
    }

    public void setSearchKeyWords(String searchKeyWords) {
        this.searchKeyWords = searchKeyWords;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "LuceneSearchDTO{" +
                "start=" + start +
                ", size=" + size +
                ", searchKeyWords='" + searchKeyWords + '\'' +
                ", tag='" + tag + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
