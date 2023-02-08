package com.bts.yomojomo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bts.yomojomo.domain.FinalActiveLocal;
import com.bts.yomojomo.domain.FinalPurpose;
import com.bts.yomojomo.domain.MyPage;
import com.bts.yomojomo.service.MyPageService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@RestController
public class MyPageController {

  @Autowired
  MyPageService myPageService;

  @RequestMapping("/mypage/list")
  public Object list() {
    return myPageService.list();
  }

  @RequestMapping("/mypage/get")
  public Object get(int no) {
    return myPageService.get(no);
  }

  @RequestMapping("/mypage/update")
  public Object update(MyPage mypage, MultipartFile file) {
    try {
      mypage.setPhoto(saveFile(file));
      System.out.println(file);
      return myPageService.update(mypage);

    } catch (Exception e) {
      e.printStackTrace();
      return "error!";
    }
  }

  @RequestMapping("/mypage/deleteCategory")
  public Object deleteCategory(MyPage mypage) {
    return myPageService.deleteCategory(mypage);
  }

  @RequestMapping(value="/mypage/insertCategory", method=RequestMethod.POST)
  @ResponseBody
  public Object insertCategory(
      @RequestParam(value="number")int no,
      @RequestParam(value="localArr[]")String[] localArr,
      @RequestParam(value="purposeArr[]")String[] purposeArr
      ) throws Exception {

    MyPage mypage = new MyPage();

    ArrayList<FinalActiveLocal> locallist = new ArrayList<>();
    for (int i = 0; i < localArr.length; i++) {
      String value = localArr[i];
      FinalActiveLocal finalactivelocal = new FinalActiveLocal(Integer.parseInt(value));
      locallist.add(finalactivelocal);
    }
    ArrayList<FinalPurpose> purposelist = new ArrayList<>();
    for (int i = 0; i < purposeArr.length; i++) {
      String value = purposeArr[i];
      FinalPurpose finalpurpose = new FinalPurpose(Integer.parseInt(value));
      purposelist.add(finalpurpose);
    }
    mypage.setNo(no);
    mypage.setFinalActiveLocal(locallist);
    mypage.setFinalPurpose(purposelist);

    return myPageService.insertCategory(mypage);
  }

  @RequestMapping("/mypage/mypost")
  public Object myPost(int no) {
    return myPageService.myPost(no);
  }

  @RequestMapping("/mypage/myscrap")
  public Object myScrap(int no) {
    return myPageService.myScrap(no);
  }

  @RequestMapping("/mypage/mynotice")
  public Object myNotice(int no) {
    return myPageService.myNotice(no);
  }

  @RequestMapping("/mypage/photo")
  public ResponseEntity<Resource> photo(String filename) {

    try {
      // 다운로드할 파일의 입력 스트림 자원을 준비한다.
      File downloadFile = new File("./upload/member/" + filename); // 다운로드 상대 경로 준비
      FileInputStream fileIn = new FileInputStream(downloadFile.getCanonicalPath()); // 다운로드 파일의 실제 경로를 지정하여 입력 스트림 준비
      InputStreamResource resource = new InputStreamResource(fileIn); // 입력 스트림을 입력 자원으로 포장

      // HTTP 응답 헤더를 준비한다.
      HttpHeaders header = new HttpHeaders();
      header.add("Cache-Control", "no-cache, no-store, must-revalidate");
      header.add("Pragma", "no-cache");
      header.add("Expires", "0");

      // 다운로드 파일명을 지정하고 싶다면 다음의 응답 헤더를 추가하라!
      // => 다운로드 파일을 지정하지 않으면 요청 URL이 파일명으로 사용된다.
      header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);



      //      // HTTP 응답 생성기를 사용하여 다운로드 파일의 응답 데이터를 준비한다.
      //      BodyBuilder http응답생성기 = ResponseEntity.ok(); // 요청 처리에 성공했다는 응답 생성기를 준비한다.
      //      http응답생성기.headers(header); // HTTP 응답 헤더를 설정한다.
      //      http응답생성기.contentLength(downloadFile.length()); // 응답 콘텐트의 파일 크기를 설정한다.
      //      http응답생성기.contentType(MediaType.APPLICATION_OCTET_STREAM); // 응답 데이터의 MIME 타입을 설정한다.
      //      
      //      // 응답 데이터를 포장한다.
      //      ResponseEntity<Resource> 응답데이터 = http응답생성기.body(resource);
      //      
      //      return 응답데이터; // 포장한 응답 데이터를 클라이언트로 리턴한다.

      return ResponseEntity.ok() // HTTP 응답 프로토콜에 따라 응답을 수행할 생성기를 준비한다.
          .headers(header) // 응답 헤더를 설정한다.
          .contentLength(downloadFile.length()) // 응답할 파일의 크기를 설정한다.
          .contentType(MediaType.APPLICATION_OCTET_STREAM) // 응답 콘텐트의 MIME 타입을 설정한다.
          .body(resource); // 응답 콘텐트를 생성한 후 리턴한다.

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private String saveFile(MultipartFile file) throws Exception {
    if (file != null && file.getSize() > 0) {
      // 파일을 저장할 때 사용할 파일명을 준비한다.
      String filename = UUID.randomUUID().toString();

      // 파일명의 확장자를 알아낸다.
      int dotIndex = file.getOriginalFilename().lastIndexOf(".");
      if (dotIndex != -1) {
        filename += file.getOriginalFilename().substring(dotIndex);
      }

      // 파일을 지정된 폴더에 저장한다.
      File photoFile = new File("./upload/member/" + filename); // App 클래스를 실행하는 프로젝트 폴더
      file.transferTo(photoFile.getCanonicalFile()); // 프로젝트 폴더의 전체 경로를 전달한다.

      // 썸네일 이미지 파일 생성
      Thumbnails.of(photoFile)
      .size(150, 200)
      .crop(Positions.CENTER)
      .outputFormat("jpg")
      .toFile(new File("./upload/member/" + "150x200_" + filename));

      return filename;

    } else {
      return null;
    }
  }
}
