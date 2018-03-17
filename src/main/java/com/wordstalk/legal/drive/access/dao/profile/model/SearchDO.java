package com.wordstalk.legal.drive.access.dao.profile.model;

import com.wordstalk.legal.drive.controller.common.CommonField;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;

/**
 * Created by guoyong on 2018/1/20.
 */
public class SearchDO {

    private long start;
    private long size;
    private String searchKeyWords;
    private int status;

    public SearchDO() {
        this.start = -1;
        this.status = CommonField.STATUS_ENABLE;
    }

    public SearchDO(SearchVO searchVO) {
        this.size = searchVO.getPageSize();
        if(searchVO.getPageNum() <= 0){
            this.start = 0;
        } else {
            this.start = (searchVO.getPageNum() - 1) * CommonField.DEFAULT_PAGE_SIZE;
        }
        this.searchKeyWords = searchVO.getSearchKeyWords();
        this.status = CommonField.STATUS_ENABLE;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SearchDO{" +
                "start=" + start +
                ", size=" + size +
                ", searchKeyWords='" + searchKeyWords + '\'' +
                ", status=" + status +
                '}';
    }
}
