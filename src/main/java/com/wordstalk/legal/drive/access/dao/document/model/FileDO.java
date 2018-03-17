package com.wordstalk.legal.drive.access.dao.document.model;

import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;
import com.wordstalk.legal.drive.controller.common.CommonField;

import java.io.File;
import java.time.LocalDateTime;

/**
 * Created by guoyong on 2018/1/28.
 */
public class FileDO {
    private long id;
    private String fileName;
    private String filePath;
    private int fileType;
    private String teamId;
    private String ownerId;
    private String parentId;
    private String tagList;
    private LocalDateTime modifyTime;
    private int status;
    private LocalDateTime createTime;

    public FileDO(){}

    /**
     * 创建team时默认生成的文件夹
     * @param teamDO
     * @param folderName
     */
    public FileDO(TeamDO teamDO, String folderName) {
        this.fileName = "";
        this.filePath = CommonField.DEFAULT_ROOT_PATH + File.separator + folderName;
        this.fileType = CommonField.NORMAL_FOLDER;
        this.teamId = teamDO.getId()+"";
        this.ownerId = teamDO.getOwnerId()+"";
        this.parentId = "1";
        this.tagList = "";
        this.modifyTime = LocalDateTime.now();
        this.status = 1;
        this.createTime = LocalDateTime.now();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FileDO{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileType=" + fileType +
                ", teamId=" + teamId +
                ", ownerId=" + ownerId +
                ", tagList='" + tagList + '\'' +
                ", modifyTime=" + modifyTime +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
