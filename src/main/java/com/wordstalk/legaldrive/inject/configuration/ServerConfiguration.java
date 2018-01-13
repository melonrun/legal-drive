package com.wordstalk.legaldrive.inject.configuration;

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
}
