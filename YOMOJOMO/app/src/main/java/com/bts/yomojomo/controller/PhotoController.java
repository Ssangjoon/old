//package com.bts.yomojomo.controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.UUID;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.InputStreamResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//import com.bts.yomojomo.dao.PhotoDao;
//import com.bts.yomojomo.domain.Photo;
//import net.coobird.thumbnailator.Thumbnails;
//import net.coobird.thumbnailator.geometry.Positions;
//
//@RestController
//public class PhotoController {
//  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
//  PhotoDao photoDao;
//
//  @RequestMapping("/photo/add")
//  public Object add(Photo photo) {
//    return photoDao.insert(photo);
//  }
//
//  @RequestMapping("/photo/get")
//  public Object get(int no) {
//    return photoDao.findByNo(no);
//  }
//
////  @RequestMapping("/photo/list")
////  public Object list() {
////    return photoDao.findAll(); 
////  }
//
//  @RequestMapping("/photo/list")
//  public ResponseEntity<Resource> photo(String filename) {
//
//    try {
//      // 다운로드할 파일의 입력 스트림 자원을 준비한다.
//      File downloadFile = new File("./upload/book/" + filename); // 다운로드 상대 경로 준비
//      FileInputStream fileIn = new FileInputStream(downloadFile.getCanonicalPath()); // 다운로드 파일의 실제 경로를 지정하여 입력 스트림 준비
//      InputStreamResource resource = new InputStreamResource(fileIn); // 입력 스트림을 입력 자원으로 포장
//
//      // HTTP 응답 헤더를 준비한다.
//      HttpHeaders header = new HttpHeaders();
//      header.add("Cache-Control", "no-cache, no-store, must-revalidate");
//      header.add("Pragma", "no-cache");
//      header.add("Expires", "0");
//
//      // 다운로드 파일명을 지정하고 싶다면 다음의 응답 헤더를 추가하라!
//      // => 다운로드 파일을 지정하지 않으면 요청 URL이 파일명으로 사용된다.
//      header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
//
//      return ResponseEntity.ok() // HTTP 응답 프로토콜에 따라 응답을 수행할 생성기를 준비한다.
//          .headers(header) // 응답 헤더를 설정한다.
//          .contentLength(downloadFile.length()) // 응답할 파일의 크기를 설정한다.
//          .contentType(MediaType.APPLICATION_OCTET_STREAM) // 응답 콘텐트의 MIME 타입을 설정한다.
//          .body(resource); // 응답 콘텐트를 생성한 후 리턴한다.
//
//    } catch (Exception e) {
//      //e.printStackTrace();
//      System.out.println("요청한 파일이 없습니다!");
//      return null;
//    }
//  }
//
//  private String saveFile(MultipartFile file) throws Exception {
//    if (file != null && file.getSize() > 0) { 
//      // 파일을 저장할 때 사용할 파일명을 준비한다.
//      String filename = UUID.randomUUID().toString();
//
//      // 파일명의 확장자를 알아낸다.
//      int dotIndex = file.getOriginalFilename().lastIndexOf(".");
//      if (dotIndex != -1) {
//        filename += file.getOriginalFilename().substring(dotIndex);
//      }
//
//      // 파일을 지정된 폴더에 저장한다.
//      File photoFile = new File("./upload/book/" + filename); // App 클래스를 실행하는 프로젝트 폴더
//      file.transferTo(photoFile.getCanonicalFile()); // 프로젝트 폴더의 전체 경로를 전달한다.
//
//      // 썸네일 이미지 파일 생성
//      Thumbnails.of(photoFile)
//      .size(50, 50)
//      .crop(Positions.CENTER)
//      .outputFormat("jpg")
//      .toFile(new File("./upload/book/" + "50x50_" + filename));
//
//      return filename;
//
//    } else {
//      return null;
//    }
//  }
//
//  //  @RequestMapping("/photo/update")
//  //  public Object update(Photo photo) {
//  //    return photoDao.update(photo);
//  //  }
//  //
//  //  @RequestMapping("/photo/delete")
//  //  public Object delete(int no) {
//  //    return photoDao.delete(no);
//  //  }
//}
