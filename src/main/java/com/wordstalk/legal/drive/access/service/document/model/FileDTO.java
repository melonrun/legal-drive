package com.wordstalk.legal.drive.access.service.document.model;

import com.wordstalk.legal.drive.access.dao.document.model.FileDO;
import com.wordstalk.legal.drive.controller.common.CommonField;
import com.wordstalk.legal.drive.lucene.model.LuceneDocDO;

import java.time.LocalDateTime;

/**
 * Created by guoyong on 2018/1/28.
 */
public class FileDTO {
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
    private String content;

    public FileDTO() {

    }

    public FileDTO(String fileName, String filePath, int fileType, String teamId, String ownerId,
                   String parentId, String tagList, LocalDateTime modifyTime, LocalDateTime createTime) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.teamId = teamId;
        this.ownerId = ownerId;
        this.parentId = parentId;
        this.tagList = tagList;
        this.modifyTime = modifyTime;
        this.status = CommonField.STATUS_ENABLE;
        this.createTime = createTime;
    }

    public FileDTO(FileDO fileDO) {
        this.id = fileDO.getId();
        this.fileName = fileDO.getFileName();
        this.filePath = fileDO.getFilePath();
        this.fileType = fileDO.getFileType();
        this.teamId = fileDO.getTeamId();
        this.ownerId = fileDO.getOwnerId();
        this.tagList = fileDO.getTagList();
        this.modifyTime = fileDO.getModifyTime();
        this.status = fileDO.getStatus();
        this.createTime = fileDO.getCreateTime();
    }

    public FileDO toDO() {
        FileDO fileDO = new FileDO();
        fileDO.setId(this.getId());
        fileDO.setFileName(this.getFileName());
        fileDO.setFilePath(this.getFilePath());
        fileDO.setFileType(this.getFileType());
        fileDO.setTeamId(this.getTeamId());
        fileDO.setOwnerId(this.getOwnerId());
        fileDO.setParentId(this.getParentId());
        fileDO.setTagList(this.getTagList());
        fileDO.setModifyTime(this.getModifyTime());
        fileDO.setStatus(this.getStatus());
        fileDO.setCreateTime(this.getCreateTime());
        return fileDO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileType=" + fileType +
                ", teamId='" + teamId + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", tagList='" + tagList + '\'' +
                ", modifyTime=" + modifyTime +
                ", status=" + status +
                ", createTime=" + createTime +
                ", content='" + content + '\'' +
                '}';
    }
}
