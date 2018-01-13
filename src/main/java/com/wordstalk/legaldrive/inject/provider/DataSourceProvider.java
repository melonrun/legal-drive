package com.wordstalk.legaldrive.inject.provider;

import com.alibaba.druid.pool.DruidDataSource;
import com.wordstalk.legaldrive.access.dao.datasource.MasterSlaveDatasource;
import org.apache.commons.configuration.ConfigurationUtils;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;
import java.net.URI;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by y on 2018/1/2.
 */
@Configuration
@Import(ConfigurationProvider.class)
public class DataSourceProvider {

    @Bean(initMethod = "init", destroyMethod = "close")
    @Qualifier("master")
    public DruidDataSource provideMasterDataSource(@Qualifier("dbMasterConf") org.apache.commons.configuration.Configuration dbMasterConfig) {
        Properties properties = new Properties();
        int maxActive = 100;
        Iterator<String> keys = dbMasterConfig.getKeys();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = dbMasterConfig.getString(key);
            if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
                throw new IllegalStateException("datasource master properties is illegal");
            }
            if ("druid.maxActive".equalsIgnoreCase(key)) {
                maxActive = Integer.parseInt(value);
            }
            properties.put(key, value);
        }


        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromPropety(properties);
        dataSource.setMaxActive(maxActive);
        return dataSource;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    @Qualifier("slave")
    public DruidDataSource provideSlaveDataSource(@Qualifier("dbSlaveConf") org.apache.commons.configuration.Configuration dbSlaveConfig) {
        Properties properties = new Properties();
        int maxActive = 100;
        Iterator<String> keys = dbSlaveConfig.getKeys();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = dbSlaveConfig.getString(key);
            if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
                throw new IllegalStateException("datasource slave properties is illegal");
            }
            if ("druid.maxActive".equalsIgnoreCase(key)) {
                maxActive = Integer.parseInt(value);
            }
            properties.put(key, value);
        }

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.configFromPropety(properties);
        dataSource.setMaxActive(maxActive);
        return dataSource;
    }

    @Bean(name = "masterTx")
    public PlatformTransactionManager provideDataSourceTransactionManager(MasterSlaveDatasource datasource) {
        return new DataSourceTransactionManager(datasource);
    }

    @Bean
    public MasterSlaveDatasource provideMasterSlaveDatasource(@Qualifier("master") DruidDataSource masterDataSource,
                                                              @Qualifier("slave") DruidDataSource slaveDataSource) {
        return new MasterSlaveDatasource(masterDataSource, slaveDataSource);
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactoryBean provideSqlSessionFactory(MasterSlaveDatasource datasource) {
        Resource resource = null;
        try {
            String prop = "mybatis-config.xml";
            File file = new File("conf/mybatis/" + prop);
            URI uri = file.exists() ? file.toURI() : ConfigurationUtils.locate("mybatis/" + prop).toURI();
            resource = new PathResource(uri);
        } catch (Exception e) {
            throw new IllegalStateException("Cannot locate mybatis config" , e);
        }
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(datasource);
        sqlSessionFactory.setConfigLocation(resource);
        return sqlSessionFactory;
    }

}
