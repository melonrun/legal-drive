package com.wordstalk.legal.drive.lucene.search.impl;

import com.wordstalk.legal.drive.access.service.document.model.FileDTO;
import com.wordstalk.legal.drive.access.service.profile.TagService;
import com.wordstalk.legal.drive.access.service.profile.TeamService;
import com.wordstalk.legal.drive.access.service.profile.UserService;
import com.wordstalk.legal.drive.inject.configuration.ServerConfiguration;
import com.wordstalk.legal.drive.lucene.common.LuceneComoonField;
import com.wordstalk.legal.drive.lucene.model.LuceneSearchDTO;
import com.wordstalk.legal.drive.lucene.search.SearchDocService;
import com.wordstalk.legal.drive.utils.CommonUtils;
import com.wordstalk.legal.drive.utils.exception.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParserBase;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoyong on 2018/2/1.
 */
@Service
public class SearchDocServiceImpl implements SearchDocService {

    @Autowired
    private ServerConfiguration serverConfiguration;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @Override
    public List<FileDTO> searchFile(LuceneSearchDTO luceneSearchDTO) throws ServiceException {
        IndexReader reader = null;
        try {
            reader = DirectoryReader.open(FSDirectory.open(Paths.get(serverConfiguration.getIndexPrefix())));
            IndexSearcher indexSearcher = new IndexSearcher(reader);

            Query query = this.genQueryBuilder(luceneSearchDTO).build();

//            if (luceneSearchDTO.getStart() > 1) {
//                int num = (int) (luceneSearchDTO.getSize() * (luceneSearchDTO.getStart() - 1));
//                TopDocs topDocs = indexSearcher.search(query, num);
//                ScoreDoc lastDoc = topDocs.scoreDocs[num - 1];
//                TopDocs result = indexSearcher.searchAfter(lastDoc, query, (int) luceneSearchDTO.getSize());
//                ScoreDoc[] scoreDocs = result.scoreDocs;
//
//                return this.getFileDTOList(indexSearcher, scoreDocs);
//            } else {

            return this.getFileDTOList(indexSearcher, query, luceneSearchDTO.getSearchKeyWords());
//            }
        } catch (Exception e) {
            throw new ServiceException("Search file error.", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<FileDTO> getFileDTOList(IndexSearcher indexSearcher, Query query, String searchKey) throws Exception {
        TopDocs topDocs = indexSearcher.search(query, 10);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        //                return this.getFileDTOList(indexSearcher, scoreDocs);
        QueryScorer score = new QueryScorer(query, LuceneComoonField.DOC_CONTENT);
        SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<span style=\"color: red\">",
                "</span>");
        Highlighter highlighter = new Highlighter(htmlFormatter, score);
        highlighter.setTextFragmenter(new SimpleSpanFragmenter(score));
        Analyzer analyzer = new SmartChineseAnalyzer();

        List<FileDTO> fileDTOList = new ArrayList<>();
        for (ScoreDoc scoreDoc : scoreDocs) {
            Document document = indexSearcher.doc(scoreDoc.doc);
            FileDTO fileDTO = new FileDTO();
            String highLightName = highlighter.getBestFragment(analyzer, LuceneComoonField.DOC_NAME,
                    document.get(LuceneComoonField.DOC_NAME));
            if (StringUtils.isNotEmpty(highLightName)) {
                fileDTO.setFileName(highLightName);
            } else {
                fileDTO.setFileName(document.get(LuceneComoonField.DOC_NAME));
            }
            fileDTO.setContent(this.highLightContent(document.get(LuceneComoonField.DOC_CONTENT), searchKey));

            fileDTO.setCreateTime(CommonUtils.getTimeByString(document.get(LuceneComoonField.CREATE_TIME)));
            fileDTO.setModifyTime(CommonUtils.getTimeByString(document.get(LuceneComoonField.MODIFY_TIME)));

            String tagName = tagService.getTagById(document.get(LuceneComoonField.TAG)).getTagName();
            fileDTO.setTagList(tagName);

            String teamName = teamService.getTeamById(document.get(LuceneComoonField.TEAM)).getTeamName();
            fileDTO.setTeamId(teamName);

            String userName = userService.getUserById(document.get(LuceneComoonField.OWNER));
            fileDTO.setOwnerId(userName);
            fileDTO.setFilePath(document.get(LuceneComoonField.DOC_PATH));
            fileDTOList.add(fileDTO);
        }
        return fileDTOList;
    }

    private String highLightContent(String content, String searchKeyWord) throws Exception {
        if (StringUtils.isNotEmpty(content)) {
            content = content.replaceAll(" ", "&nbsp;")
                    .replaceAll("\r\n", "<br/>").replaceAll("\n", "<br/>");
        }
        if (StringUtils.isEmpty(searchKeyWord)){
            return content;
        }
        TokenStream tokenStream = null;
        try {
            Analyzer analyzer = new SmartChineseAnalyzer();
            tokenStream = analyzer.tokenStream("searchKeyWord", new StringReader(searchKeyWord));
            tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
                String keyWord = new String(charTermAttribute.toString());
                content = content.replaceAll(keyWord, "<span style=\"color: red\">" + keyWord + "</span>");
            }
            return content;
        } catch (Exception e) {
            throw e;
        } finally {
            if (tokenStream != null) {
                tokenStream.close();
            }
        }
    }

    private BooleanQuery.Builder genQueryBuilder(LuceneSearchDTO luceneSearchDTO) throws ParseException {
        Analyzer analyzer = new SmartChineseAnalyzer();
        BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();
        //1. createTime
        if (luceneSearchDTO.getCreateTime() != null) {
            long dayBegin = luceneSearchDTO.getCreateTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            long dayEnd = dayBegin + 86399000L;
            queryBuilder.add(TermRangeQuery.newStringRange(LuceneComoonField.CREATE_TIME, new Long(dayBegin).toString(),
                    new Long(dayEnd).toString(), true, true),
                    BooleanClause.Occur.MUST);
        } else {
            queryBuilder.add(TermRangeQuery.newStringRange(LuceneComoonField.CREATE_TIME, "0",
                    Long.MAX_VALUE + "", true, true),
                    BooleanClause.Occur.MUST);
        }
        //2. modifyTime
        if (luceneSearchDTO.getModifyTime() != null) {
            long dayBegin = luceneSearchDTO.getModifyTime().toEpochSecond(ZoneOffset.of("+8"));
            long dayEnd = dayBegin + 86399L;
            queryBuilder.add(TermRangeQuery.newStringRange(LuceneComoonField.MODIFY_TIME, new Long(dayBegin).toString(),
                    new Long(dayEnd).toString(), true, true),
                    BooleanClause.Occur.MUST);
        }
        //3. tag
        if (StringUtils.isNotEmpty(luceneSearchDTO.getTag())) {
            QueryParser parser = new QueryParser(LuceneComoonField.TAG, analyzer);
            Query query = parser.parse(luceneSearchDTO.getTag());
            queryBuilder.add(query, BooleanClause.Occur.MUST);
        }
        //4. content
        if (StringUtils.isNotEmpty(luceneSearchDTO.getSearchKeyWords())) {
            QueryParser contentParser = new QueryParser(LuceneComoonField.DOC_CONTENT, analyzer);
            contentParser.setDefaultOperator(QueryParserBase.AND_OPERATOR);
            Query contentQuery = contentParser.parse(luceneSearchDTO.getSearchKeyWords());
            queryBuilder.add(contentQuery, BooleanClause.Occur.MUST);
        }
        return queryBuilder;
    }

    public static void main(String[] args) throws IOException {
        Analyzer analyzer = new SmartChineseAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("aaa",
                new StringReader("lucene 是一个全文检索的高大上的工具包"));
        tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
            System.out.println(new String(charTermAttribute.toString()));
        }
    }

}