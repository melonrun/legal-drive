package com.wordstalk.legal.drive.lucene.resolver.impl;

import com.wordstalk.legal.drive.access.service.document.model.FileDTO;
import com.wordstalk.legal.drive.inject.configuration.ServerConfiguration;
import com.wordstalk.legal.drive.lucene.common.LuceneComoonField;
import com.wordstalk.legal.drive.lucene.model.LuceneDocDO;
import com.wordstalk.legal.drive.lucene.resolver.CommonResolver;
import com.wordstalk.legal.drive.utils.CommonUtils;
import com.wordstalk.legal.drive.utils.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Paths;
import java.time.ZoneOffset;
import java.util.Base64;

/**
 * Created by guoyong on 2018/2/4.
 */
@Service
public class CommonResolverImpl implements CommonResolver {

    @Autowired
    private PdfDocResolverImpl pdfDocResolver;

    @Autowired
    private WordResolverImpl wordResolver;

    @Autowired
    protected ServerConfiguration serverConfiguration;

    @Override
    public LuceneDocDO convertDocDO(FileDTO fileDTO) throws ServiceException {
        try {
            LuceneDocDO luceneDocDO = new LuceneDocDO();
            luceneDocDO.setCreateTime(CommonUtils.getLongByTime(fileDTO.getCreateTime()));
            luceneDocDO.setModifyTime(CommonUtils.getLongByTime(fileDTO.getModifyTime()));
            luceneDocDO.setDocName(fileDTO.getFileName());
            luceneDocDO.setDocPath(fileDTO.getFilePath());
            luceneDocDO.setTeam(fileDTO.getTeamId());
            luceneDocDO.setTag(fileDTO.getTagList());
            luceneDocDO.setOwner(fileDTO.getOwnerId());
            String fileType = CommonUtils.typeOfFile(fileDTO.getFileName());
            fileDTO.setFileName(new String(Base64.getEncoder().encodeToString(fileDTO.getFileName().getBytes())));
            if (LuceneComoonField.FILE_TYPE_PDF.equals(fileType)) {
                String content = pdfDocResolver.convertDocDO(Paths.get(this.getRealPath(fileDTO.getFilePath())
                        + File.separator + fileDTO.getFileName()));
                luceneDocDO.setContent(luceneDocDO.getDocName() + "\r\n" + content);
                return luceneDocDO;
            }
            if (LuceneComoonField.FILE_TYPE_WORD.equals(fileType)) {
                String content = wordResolver.convertDocDO(Paths.get(this.getRealPath(fileDTO.getFilePath())
                        + File.separator + fileDTO.getFileName()));
                luceneDocDO.setContent(luceneDocDO.getDocName() + "\r\n" + content);
                return luceneDocDO;
            }
            throw new ServiceException("Not support file.");
        } catch (Exception e) {
            throw new ServiceException("file convert error.", e);
        }
    }

    private String getRealPath(String viewPath) {
        String filePrefix = serverConfiguration.getFilePrefix();
        String realPath = filePrefix + File.separator + viewPath;
        return realPath;
    }
}
