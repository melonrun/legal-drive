package com.wordstalk.legal.drive.inject.provider;

import com.wordstalk.legal.drive.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by y on 2018/1/2.
 */
@Configuration
public class ConfigurationProvider {

    private final String CONF_DIR = "conf";
    private String dbMasterProp = "datasource_master.properties";
    private String dbSlaveProp = "datasource_slave.properties";

    @Bean("dbMasterConf")
    @Qualifier("dbMasterConf")
    public org.apache.commons.configuration.Configuration provideDBMasterConfiguration() {
        return PropertyUtils.loadProperty(CONF_DIR, dbMasterProp, 0L);
    }

    @Bean("dbSlaveConf")
    @Qualifier("dbSlaveConf")
    public org.apache.commons.configuration.Configuration provideDBSlaveConfiguration() {
        return PropertyUtils.loadProperty(CONF_DIR, dbSlaveProp, 0L);
    }
}
