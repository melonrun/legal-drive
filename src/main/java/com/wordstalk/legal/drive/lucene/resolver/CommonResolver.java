package com.wordstalk.legal.drive.lucene.resolver;

import com.wordstalk.legal.drive.access.service.document.model.FileDTO;
import com.wordstalk.legal.drive.lucene.model.LuceneDocDO;
import com.wordstalk.legal.drive.utils.exception.ServiceException;

/**
 * Created by guoyong on 2018/2/4.
 */
public interface CommonResolver {

    LuceneDocDO convertDocDO(FileDTO fileDTO) throws ServiceException;
}
