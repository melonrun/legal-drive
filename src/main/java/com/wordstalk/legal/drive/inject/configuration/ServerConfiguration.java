package com.wordstalk.legal.drive.inject.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by y on 2018/1/7.
 */
@Component
public class ServerConfiguration {

    @Value("${server.name}")
    private String name;
    @Value("${server.version}")
    private String version;
    @Value("${server.file.dir.prefix}")
    private String filePrefix;
    @Value("${server.index.dir.prefix}")
    private String indexPrefix;

    public String getFilePrefix() {
        return filePrefix;
    }

    public void setFilePrefix(String filePrefix) {
        this.filePrefix = filePrefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIndexPrefix() {
        return indexPrefix;
    }

    public void setIndexPrefix(String indexPrefix) {
        this.indexPrefix = indexPrefix;
    }
}
