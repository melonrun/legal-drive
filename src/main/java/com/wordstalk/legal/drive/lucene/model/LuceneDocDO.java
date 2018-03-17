package com.wordstalk.legal.drive.lucene.model;

/**
 * Created by guoyong on 2018/2/1.
 */
public class LuceneDocDO {
    private String docName;
    private String docPath;
    private String content;
    private String tag;
    private String team;
    private String owner;
    private Long createTime;
    private Long modifyTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "LuceneDocDO{" +
                "docName='" + docName + '\'' +
                ", docPath='" + docPath + '\'' +
                ", content='" + content + '\'' +
                ", tag='" + tag + '\'' +
                ", team='" + team + '\'' +
                ", owner='" + owner + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
