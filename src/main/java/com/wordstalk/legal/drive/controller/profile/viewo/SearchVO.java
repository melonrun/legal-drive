package com.wordstalk.legal.drive.controller.profile.viewo;

import com.wordstalk.legal.drive.controller.common.CommonField;

import javax.validation.constraints.Min;

/**
 * Created by guoyong on 2018/1/20.
 */
public class SearchVO {
    private long pageSize = CommonField.DEFAULT_PAGE_SIZE;
    @Min(value = 1)
    private long pageNum = 1;
    private String searchKeyWords;

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public String getSearchKeyWords() {
        return searchKeyWords;
    }

    public void setSearchKeyWords(String searchKeyWords) {
        this.searchKeyWords = searchKeyWords;
    }

    @Override
    public String toString() {
        return "SearchVO{" +
                "pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                ", searchKeyWords='" + searchKeyWords + '\'' +
                '}';
    }
}
