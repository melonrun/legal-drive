package com.wordstalk.legal.drive.utils;

import com.wordstalk.legal.drive.access.dao.profile.model.TeamDO;
import com.wordstalk.legal.drive.lucene.common.LuceneComoonField;
import org.apache.commons.lang.StringUtils;
import org.springframework.cglib.core.Local;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by guoyong on 2018/1/20.
 */
public class CommonUtils {

    public static String getErrorByBindResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            bindingResult.getAllErrors().forEach(error -> sb.append(error.getDefaultMessage()).append(","));
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        return null;
    }

    public static Map<Long, TeamDO> getTeamMapByList(List<TeamDO> teamList) {
        Map<Long, TeamDO> result = new HashMap<>();
        if (CollectionUtils.isEmpty(teamList)) {
            return result;
        }
        for (TeamDO teamDO : teamList) {
            result.put(teamDO.getId(), teamDO);
        }
        return result;
    }

    public static LocalDateTime formatDateTime(String sourceDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(sourceDate, df);
    }

    public static String getTeamFolderName(TeamDO teamDO){
        return teamDO.getId() + "-" + teamDO.getTeamName();
    }

    public static String typeOfFile(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (".pdf".equalsIgnoreCase(suffix)) {
            return LuceneComoonField.FILE_TYPE_PDF;
        }
        if (".doc".equalsIgnoreCase(suffix) || ".docx".equalsIgnoreCase(suffix)) {
            return LuceneComoonField.FILE_TYPE_WORD;
        }
        return "";
    }

    public static Long getLongByTime(LocalDateTime localDateTime){
        if(localDateTime != null) {
            return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        }
        return 0L;
    }

    public static LocalDateTime getTimeByString(String timestamp){
        if(StringUtils.isEmpty(timestamp)){
            return LocalDateTime.now();
        }
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(
                Long.valueOf(timestamp)), ZoneOffset.of("+8"));
    }

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        System.out.println(StringUtils.join(list.toArray(), ","));
        List<String> listStr = new ArrayList<>();
        listStr.add("1");
        listStr.add("2");
        listStr.add("3");
        listStr.add("4");
        System.out.println(StringUtils.join(list.toArray(), ","));
        String[] strs = new String[]{"1","2","3"};
        System.out.println(Arrays.asList(strs));
    }

}
