package com.wordstalk.legal.drive.access.dao.datasource;

import com.wordstalk.legal.drive.access.dao.annotation.UseMaster;
import com.wordstalk.legal.drive.access.dao.annotation.UseSlave;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by y on 2018/1/2.
 */
public class DataSourceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);


    @Around("@annotation(useMaster))")
    public Object doAroundMaster(ProceedingJoinPoint pjp, UseMaster useMaster) throws Throwable {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[Thread:{}] {} using master datasource", Thread.currentThread().getName(), pjp.getSignature().getName());
        }
        if (null == useMaster) {
            LOGGER.warn("Annotation [UseMaster] is Null, Will using MASTER as default");
        }
        MasterSlaveDatasource.usingMaster();
        Object retVal;
        try {
            retVal = pjp.proceed();
        } /*catch (Throwable e) {
            LOGGER.error(pjp.getSignature().getName() +" proceed exception", e);
            throw new Exception(e);
        }*/ finally {
            MasterSlaveDatasource.clear();
        }
        return retVal;
    }

    @Around("@annotation(useSlave))")
    public Object doAroundSlave(ProceedingJoinPoint pjp, UseSlave useSlave) throws Throwable {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[Thread:{}] {} using slave datasource", Thread.currentThread().getName(), pjp.getSignature().getName());
        }
        if (null == useSlave) {
            LOGGER.warn("Annotation [UseSlave] is Null, Will using MASTER as default");
            MasterSlaveDatasource.usingMaster();
        } else {
            MasterSlaveDatasource.usingSlave();
        }

        Object retVal;
        try {
            retVal = pjp.proceed();
        } /*catch (Throwable e) {
            LOGGER.error(pjp.getSignature().getName() +" proceed exception", e);
            throw new Exception(e);
        } */ finally {
            MasterSlaveDatasource.clear();
        }
        return retVal;
    }

}
