package com.simple_board.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.simple_board.adm.vo.IdeaVO;
import com.simple_board.adm.vo.NoticeVO;

@Component("fileUtils")
public class FileUtils {
  private static final String filePath = "C:\\upload\\"; // 파일이 저장될 위치

  public List<Map<String, Object>> parseInsertFileInfo(NoticeVO noticeVO, 
      MultipartHttpServletRequest mpRequest) throws Exception{

    List<MultipartFile> fileList = mpRequest.getFiles("file");

    String originalFileName = null;
    String originalFileExtension = null;
    String storedFileName = null;

    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Map<String, Object> listMap = null;

    int bno = noticeVO.getSeq();

    File file = new File(filePath);
    if(file.exists() == false) {
      file.mkdirs();
    }

    for (MultipartFile mf : fileList) {
      String originFileName = mf.getOriginalFilename(); // 원본 파일 명
      if(originFileName != "") {
        originalFileName = mf.getOriginalFilename();
        originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        storedFileName = getRandomString() + originalFileExtension;

        file = new File(filePath + storedFileName);
        mf.transferTo(file);
        listMap = new HashMap<String, Object>();
        listMap.put("notice_seq", bno);
        listMap.put("org_file", originalFileName);
        listMap.put("stored_file", storedFileName);
        listMap.put("file_size", mf.getSize());
        list.add(listMap);
      }
    }
    return list;
  }

  public List<Map<String, Object>> parseInsertFileInfo(IdeaVO ideaVO, 
      MultipartHttpServletRequest mpRequest) throws Exception{

    List<MultipartFile> fileList = mpRequest.getFiles("file");

    String originalFileName = null;
    String originalFileExtension = null;
    String storedFileName = null;

    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    Map<String, Object> listMap = null;

    int bno = ideaVO.getSeq();

    File file = new File(filePath);
    if(file.exists() == false) {
      file.mkdirs();
    }

    for (MultipartFile mf : fileList) {
      String originFileName = mf.getOriginalFilename(); // 원본 파일 명
      if(originFileName != "") {
        originalFileName = mf.getOriginalFilename();
        originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        storedFileName = getRandomString() + originalFileExtension;

        file = new File(filePath + storedFileName);
        mf.transferTo(file);
        listMap = new HashMap<String, Object>();
        listMap.put("notice_seq", bno);
        listMap.put("org_file", originalFileName);
        listMap.put("stored_file", storedFileName);
        listMap.put("file_size", mf.getSize());
        list.add(listMap);
      }
    }
    return list;
  }

  public static String getRandomString() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }
}