package com.wordstalk.legal.drive.lucene.model;

/**
 * Created by guoyong on 2018/2/4.
 */
public class LuceneDocDTO {

    private String docName;
    private String docPath;
    private String content;
    private String tag;
    private Long createTime;
    private Long modifyTime;

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

    @Override
    public String toString() {
        return "LuceneDocDTO{" +
                "docName='" + docName + '\'' +
                ", docPath='" + docPath + '\'' +
                ", content='" + content + '\'' +
                ", tag='" + tag + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
