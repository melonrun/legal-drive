package com.wordstalk.legal.drive.lucene.search;


import com.wordstalk.legal.drive.access.service.document.model.FileDTO;
import com.wordstalk.legal.drive.lucene.model.LuceneSearchDTO;
import com.wordstalk.legal.drive.utils.exception.ServiceException;

import java.util.List;

/**
 * Created by guoyong on 2018/2/1.
 */
public interface SearchDocService {

    List<FileDTO> searchFile(LuceneSearchDTO luceneSearchDTO) throws ServiceException;
}
