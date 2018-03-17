import com.wordstalk.legal.drive.access.service.document.model.FileDTO;
import com.wordstalk.legal.drive.lucene.common.LuceneComoonField;
import com.wordstalk.legal.drive.lucene.model.LuceneSearchDTO;
import com.wordstalk.legal.drive.utils.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoyong on 2018/2/5.
 */
public class LuceneTest {

    @Test
    public void searchTest() throws Exception {
        LuceneSearchDTO luceneSearchDTO = new LuceneSearchDTO();
        luceneSearchDTO.setSize(10);
        luceneSearchDTO.setStart(1);
        luceneSearchDTO.setSearchKeyWords("闲话");

        IndexSearcher indexSearcher = this.getIndexSearcher();

        Query query = this.genQueryBuilder(luceneSearchDTO).build();
        TopDocs topDocs = indexSearcher.search(query, (int) luceneSearchDTO.getSize());
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        List<FileDTO> fileDTOList = this.getFileDTOList(indexSearcher, scoreDocs);
        System.out.println(fileDTOList.size());
    }

    public List<FileDTO> getFileDTOList(IndexSearcher indexSearcher, ScoreDoc[] socreDocs) throws IOException {
        List<FileDTO> fileDTOList = new ArrayList<>();
        for (ScoreDoc scoreDoc : socreDocs) {
            FileDTO fileDTO = new FileDTO();
            Document document = indexSearcher.doc(scoreDoc.doc);
            fileDTO.setFileName(document.get(LuceneComoonField.DOC_NAME));
            fileDTO.setCreateTime(CommonUtils.getTimeByString(document.get(LuceneComoonField.CREATE_TIME)));
            fileDTO.setModifyTime(CommonUtils.getTimeByString(document.get(LuceneComoonField.MODIFY_TIME)));
            fileDTO.setTagList(document.get(LuceneComoonField.TAG));
            fileDTO.setTeamId(document.get(LuceneComoonField.TEAM));
            fileDTO.setOwnerId(document.get(LuceneComoonField.OWNER));
            fileDTO.setFilePath(document.get(LuceneComoonField.DOC_PATH));
            fileDTOList.add(fileDTO);
        }
        return fileDTOList;
    }

    @Test
    public void timeLong(){
        long l = 1517929879000L;
        Instant instant = Instant.ofEpochMilli(l);
        LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneOffset.of("+8"));
        System.out.println(date.toString());
    }

    private IndexSearcher getIndexSearcher() throws IOException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("index")));
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        return indexSearcher;
    }



    public BooleanQuery.Builder genQueryBuilder(LuceneSearchDTO luceneSearchDTO) throws ParseException {
        Analyzer analyzer1 = new SmartChineseAnalyzer();

        BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();
        //1. createTime
        if (luceneSearchDTO.getCreateTime() != null) {
            long dayBegin = luceneSearchDTO.getCreateTime().toEpochSecond(ZoneOffset.of("+8"));
            long dayEnd = dayBegin + 86399L;
            queryBuilder.add(LongPoint.newRangeQuery(LuceneComoonField.CREATE_TIME, dayBegin, dayEnd),
                    BooleanClause.Occur.MUST);
        }
        //3. tag
        if (StringUtils.isNotEmpty(luceneSearchDTO.getTag())) {
            QueryParser parser = new QueryParser(LuceneComoonField.TAG, analyzer1);
            Query query = parser.parse(luceneSearchDTO.getTag());
            queryBuilder.add(query, BooleanClause.Occur.MUST);
        }
        //2. modifyTime
        if (luceneSearchDTO.getModifyTime() != null) {
            long dayBegin = luceneSearchDTO.getModifyTime().toEpochSecond(ZoneOffset.of("+8"));
            long dayEnd = dayBegin + 86399L;
            queryBuilder.add(LongPoint.newRangeQuery(LuceneComoonField.MODIFY_TIME, dayBegin, dayEnd),
                    BooleanClause.Occur.MUST);
        }
        //4. content
        QueryParser contentParser = new QueryParser(LuceneComoonField.DOC_CONTENT, analyzer1);
        Query contentQuery = contentParser.parse(luceneSearchDTO.getSearchKeyWords());
        queryBuilder.add(contentQuery, BooleanClause.Occur.SHOULD);
        //5. title
        QueryParser titleParser = new QueryParser(LuceneComoonField.DOC_NAME, analyzer1);
        Query titleQuery = titleParser.parse(luceneSearchDTO.getSearchKeyWords());
        queryBuilder.add(titleQuery, BooleanClause.Occur.SHOULD);
        return queryBuilder;
    }
}
