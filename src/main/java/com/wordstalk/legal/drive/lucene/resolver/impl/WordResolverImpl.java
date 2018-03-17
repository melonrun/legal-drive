package com.wordstalk.legal.drive.lucene.resolver.impl;

import com.wordstalk.legal.drive.lucene.common.LuceneComoonField;
import com.wordstalk.legal.drive.lucene.resolver.AbstractDocResolver;
import com.wordstalk.legal.drive.utils.CommonUtils;
import com.wordstalk.legal.drive.utils.exception.ServiceException;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Created by guoyong on 2018/2/1.
 */
@Service
public class WordResolverImpl implements AbstractDocResolver {

    @Override
    public String convertDocDO(Path file) throws ServiceException {
        WordExtractor extractor = null;
        XWPFDocument xDoc = null;
        try {
            String fileType = this.getDocType(file.toFile().getName());
            if (fileType.equals(LuceneComoonField.FILE_WORD_DOC)) {
                extractor = new WordExtractor(new FileInputStream(file.toFile()));
                String text = extractor.getText();
                return text;
            }
            if (fileType.equals(LuceneComoonField.FILE_WORD_DOCX)) {
                xDoc = new XWPFDocument(new FileInputStream(file.toFile()));
                XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(xDoc);
                String text = xwpfWordExtractor.getText();
                return text;
            }
        } catch (Exception e) {
            throw new ServiceException("convert word file error.", e);
        } finally {
            try {
                if (extractor != null) {
                    extractor.close();
                }
                if (xDoc != null) {
                    xDoc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private String getDocType(String fileName) {
        fileName = new String(Base64.getDecoder().decode(fileName));
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (".doc".equalsIgnoreCase(suffix)) {
            return LuceneComoonField.FILE_WORD_DOC;
        }
        if (".docx".equalsIgnoreCase(suffix)) {
            return LuceneComoonField.FILE_WORD_DOCX;
        }
        return "";
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        WordExtractor extractor = new WordExtractor(new FileInputStream("files/test/456.DOC"));
        System.out.println(extractor.getText());

        Path path = Paths.get("files/test/456.DOC");
        System.out.println(path.toFile().getName());

        XWPFDocument xdoc = new XWPFDocument(new FileInputStream(new File("files/test/789.docx")));
        XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(xdoc);
        System.out.println(xwpfWordExtractor.getText());
    }
}
