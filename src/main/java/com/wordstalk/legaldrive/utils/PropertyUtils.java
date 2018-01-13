package com.wordstalk.legaldrive.utils;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;

/**
 * Created by y on 2018/1/2.
 */
public class PropertyUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtils.class);

    public PropertyUtils() {
    }

    public static Configuration loadProperty(String prop, long refreshDelay) {
        return loadProperty((String)null, prop, refreshDelay);
    }

    public static Configuration loadProperties(String[] props, long refreshDelay) {
        return loadProperties((String)null, props, refreshDelay);
    }

    public static Configuration loadProperty(String confDir, String prop, long refreshDelay) {
        return loadProperties(confDir, new String[]{prop}, refreshDelay);
    }

    public static Configuration loadProperties(String confDir, String[] props, long refreshDelay) {
        CompositeConfiguration config = new CompositeConfiguration();
        String propDir = "";
        if (!StringUtils.isBlank(confDir)) {
            propDir = confDir + "/";
        }

        String[] var6 = props;
        int var7 = props.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            String prop = var6[var8];
            if (StringUtils.isBlank(prop)) {
                LOGGER.warn("property file [{}] cannot be blank", prop);
            } else {
                File file = new File(propDir + prop);
                URL url = null;

                try {
                    url = file.exists() ? file.toURI().toURL() : ConfigurationUtils.locate(prop);
                    LOGGER.info("loading conf from:" + url);
                    PropertiesConfiguration fileConfiguraton = new PropertiesConfiguration();
                    fileConfiguraton.setDelimiterParsingDisabled(true);
                    fileConfiguraton.setEncoding("utf8");
                    fileConfiguraton.load(url);
                    if (5000L <= refreshDelay) {
                        FileChangedReloadingStrategy reloadingStrategy = new FileChangedReloadingStrategy();
                        reloadingStrategy.setRefreshDelay(refreshDelay);
                        fileConfiguraton.setReloadingStrategy(reloadingStrategy);
                    }

                    config.addConfiguration(fileConfiguraton);
                } catch (Exception var14) {
                    LOGGER.error("Failed to load config:" + prop, var14);
                }
            }
        }

        return config;
    }

}
