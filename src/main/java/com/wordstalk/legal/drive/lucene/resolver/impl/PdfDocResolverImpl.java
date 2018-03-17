package com.wordstalk.legal.drive.lucene.resolver.impl;

import com.wordstalk.legal.drive.inject.configuration.ServerConfiguration;
import com.wordstalk.legal.drive.lucene.resolver.AbstractDocResolver;
import com.wordstalk.legal.drive.utils.exception.ServiceException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by guoyong on 2018/2/1.
 */
@Service
public class PdfDocResolverImpl implements AbstractDocResolver {

    @Override
    public String convertDocDO(Path path) throws ServiceException {
        PDDocument pdfDocument = null;
        try {
            pdfDocument = PDDocument.load(path.toFile());
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(pdfDocument);
            return text;
        } catch (Exception e) {
            throw new ServiceException("convert pdf file error.", e);
        } finally {
            if (pdfDocument != null) {
                try {
                    pdfDocument.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
