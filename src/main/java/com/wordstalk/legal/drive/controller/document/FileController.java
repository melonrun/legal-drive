package com.wordstalk.legal.drive.controller.document;

import com.wordstalk.legal.drive.access.service.document.FileService;
import com.wordstalk.legal.drive.access.service.document.model.FileDTO;
import com.wordstalk.legal.drive.access.service.profile.model.EntryDTO;
import com.wordstalk.legal.drive.controller.common.AbstractBaseController;
import com.wordstalk.legal.drive.controller.common.CommonField;
import com.wordstalk.legal.drive.controller.common.Result;
import com.wordstalk.legal.drive.controller.document.viewo.FilePagerVO;
import com.wordstalk.legal.drive.controller.profile.viewo.SearchVO;
import com.wordstalk.legal.drive.inject.annotation.PartnerRequired;
import com.wordstalk.legal.drive.lucene.common.LuceneComoonField;
import com.wordstalk.legal.drive.lucene.index.IndexDocService;
import com.wordstalk.legal.drive.lucene.model.LuceneSearchDTO;
import com.wordstalk.legal.drive.lucene.search.SearchDocService;
import com.wordstalk.legal.drive.utils.CommonUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

/**
 * Created by guoyong on 2018/1/27.
 */
@Controller
@RequestMapping(value = "/file")
@PartnerRequired
public class FileController extends AbstractBaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    private FileService fileService;
    private IndexDocService indexDocService;
    private SearchDocService searchDocService;

    @Autowired
    public FileController(FileService fileService, IndexDocService indexDocService,
                          SearchDocService searchDocService) {
        this.fileService = fileService;
        this.indexDocService = indexDocService;
        this.searchDocService = searchDocService;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public Object listFile(SearchVO searchVO, String parentId, HttpServletRequest request) {
        try {
            EntryDTO entryDTO = currentUser(request);
            String[] teamList;
            if (entryDTO.getRoleNameDisplay().equals(CommonField.ROLE_PARTNER_DISPLAY)) {
                teamList = new String[]{"-1"};
            } else {
                teamList = entryDTO.getTeamList().split(",");
                if (teamList.length <= 0) {
                    return initErrorResult("请检查登陆信息。");
                }
            }

            List<FileDTO> fileDTOList = fileService.listFiles(searchVO, parentId, teamList);
            long fileCount = fileService.queryFileCount(searchVO, parentId, teamList);
            String currentPath = fileService.queryCurrentPath(teamList, Long.valueOf(parentId));

            FilePagerVO filePagerVO = new FilePagerVO();
            filePagerVO.setFiles(fileDTOList);
            filePagerVO.setTotalPage((fileCount - 1) / searchVO.getPageSize() + 1);
            filePagerVO.setTotalItem(fileCount);
            filePagerVO.setCurrentPath(currentPath);
            Result<FilePagerVO> result = initOkResult(filePagerVO);
            return result;
        } catch (Exception e) {
            LOGGER.error("listTeam fail: ", e);
        }
        return initErrorResult("fail to search fileList.");
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public Object uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
                             @RequestParam(value = "fePath") String fePath, String modifyDate, String tagList,
                             String teamId, String parentId, HttpServletRequest request) {
        try {
            if ("/root".equals(fePath)) {
                throw new IllegalArgumentException("请切换路径，该路径不能上传。");
            }
            String fileType = CommonUtils.typeOfFile(file.getOriginalFilename());
            if (!LuceneComoonField.FILE_TYPE_PDF.equals(fileType) && !LuceneComoonField.FILE_TYPE_WORD.equals(fileType)) {
                return initErrorResult("目前只支持PDF，WORD文档。");
            }
            String viewPath = this.getViewPath(request, fePath);
            File realPath = new File(getRealPath(viewPath) + File.separator +
                    Base64.getEncoder().encodeToString(file.getOriginalFilename().getBytes()));
            if (!realPath.getParentFile().exists()) {
                realPath.getParentFile().mkdirs();
            }
            // 1.save disk file
            file.transferTo(realPath);
            // 2.save db file
            FileDTO fileDTO = new FileDTO(file.getOriginalFilename(), viewPath, CommonField.NORMAL_FILE,
                    teamId, currentUser(request).getId() + "", parentId, tagList,
                    CommonUtils.formatDateTime(modifyDate), LocalDateTime.now());
            fileService.insertFile(fileDTO);
            // 3.index file
            indexDocService.addSingleDoc(fileDTO);
            // 4.TODO rollback
            return initOkResult(viewPath);
        } catch (Exception e) {
            LOGGER.error("fail to upload file：" + file.getOriginalFilename(), e);
            return initErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object searchFile(@RequestBody @Valid LuceneSearchDTO luceneSearchDTO, BindingResult bindingResult) {
        try {
            String errors = CommonUtils.getErrorByBindResult(bindingResult);
            if (!StringUtils.isBlank(errors)) {
                throw new IllegalArgumentException("请确认参数是否填写正确.");
            }
            List<FileDTO> fileDTOList = searchDocService.searchFile(luceneSearchDTO);

            long fileCount = 100;
            FilePagerVO filePagerVO = new FilePagerVO();
            filePagerVO.setFiles(fileDTOList);
            filePagerVO.setTotalPage((fileCount - 1) / luceneSearchDTO.getSize() + 1);
            filePagerVO.setTotalItem(fileCount);
            Result<FilePagerVO> result = initOkResult(filePagerVO);
            return result;
        } catch (Exception e) {
            LOGGER.error("search file error");
        }
        return initErrorResult("fail to search fileList.");
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request, String fileId, String filePath,
                                               String fileName) {
        try {
            String realPath = "";
            if (!StringUtils.isEmpty(fileId)) {
                FileDTO fileDTO = fileService.getFileById(Long.valueOf(fileId));
                realPath = getRealPath(fileDTO.getFilePath()) + File.separator +
                        Base64.getEncoder().encodeToString(fileDTO.getFileName().getBytes());
            } else if (StringUtils.isNotEmpty(filePath) && StringUtils.isNotEmpty(fileName)) {
                realPath = getRealPath(filePath) + File.separator +
                        Base64.getEncoder().encodeToString(fileName.getBytes());
            }
            File file = new File(realPath);
            if (file.exists()) {
                HttpHeaders headers = new HttpHeaders();
                String fileOriName = new String(Base64.getDecoder().decode(file.getName()));
                String downloadFileName =
                        new String(fileOriName.getBytes("UTF-8"), "iso-8859-1");
                headers.add("Content-Disposition", "attchement;filename=" + downloadFileName);
                return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOGGER.error("fail to download file: " + fileId);
        }
        return null;
    }
}
