package com.wordstalk.legal.drive.access.dao.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by y on 2018/1/2.
 */
public class MasterSlaveDatasource extends AbstractRoutingDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterSlaveDatasource.class);
    private static final ThreadLocal<Role> datasourceHolder;

    static {
        datasourceHolder = new ThreadLocal<Role>() {
            @Override
            protected Role initialValue() {
                return Role.MASTER;
            }
        };
    }

    private DataSource master;
    private DataSource slave;

    public MasterSlaveDatasource(final DataSource master, final DataSource slave) {
        this.master = master;
        this.slave = slave;

        Map<Object, Object> targetDataSources = new HashMap<Object, Object>() {{
            put(Role.MASTER, master);
            put(Role.SLAVE, slave);
        }};
        this.setDefaultTargetDataSource(master);
        this.setTargetDataSources(targetDataSources);
    }

    public static void usingMaster() {
        datasourceHolder.set(Role.MASTER);
    }

    public static void usingSlave() {
        datasourceHolder.set(Role.SLAVE);
    }

    public static void clear() {
        datasourceHolder.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        Role datasourceRole = datasourceHolder.get();
        if (null == datasourceRole) {
            datasourceRole = Role.MASTER;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[Thread:{}] using {} datasource", Thread.currentThread().getName(), datasourceRole.name());
        }
        return datasourceRole;
    }


    enum Role {
        MASTER, SLAVE
    }
}
