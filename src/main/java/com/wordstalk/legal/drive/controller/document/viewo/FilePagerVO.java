package com.wordstalk.legal.drive.controller.document.viewo;

import com.wordstalk.legal.drive.access.service.document.model.FileDTO;

import java.util.List;

/**
 * Created by guoyong on 2018/1/28.
 */
public class FilePagerVO {

    private List<FileDTO> files;
    private long totalItem;
    private long totalPage;
    private String currentPath;

    public List<FileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<FileDTO> files) {
        this.files = files;
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

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

    @Override
    public String toString() {
        return "FilePagerVO{" +
                "files=" + files +
                ", totalItem=" + totalItem +
                ", totalPage=" + totalPage +
                ", currentPath='" + currentPath + '\'' +
                '}';
    }
}
