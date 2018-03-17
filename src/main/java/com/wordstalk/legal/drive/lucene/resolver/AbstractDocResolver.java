package com.wordstalk.legal.drive.lucene.resolver;


import com.wordstalk.legal.drive.utils.exception.ServiceException;

import java.nio.file.Path;

/**
 * Created by guoyong on 2018/2/1.
 */
public interface AbstractDocResolver {

    String convertDocDO(Path path) throws ServiceException;

}
