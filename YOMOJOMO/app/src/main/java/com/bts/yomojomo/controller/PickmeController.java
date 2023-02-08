package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.FAIL;
import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.domain.Pickme;
import com.bts.yomojomo.service.PickmeService;

@RestController
public class PickmeController {
  private static final Logger log = LoggerFactory.getLogger(PickmeController.class);


  @Autowired 
  PickmeService pickmeService;

  @RequestMapping("/pickme/list")
  public Object list(int pageSize, int pageNo, String nameSi, String nameGu, String keyword, HttpSession session) {
    log.info("게시물 목록 조회");

    try {
      if (pageSize < 10 || pageSize > 100) {
        pageSize = 10;
      }
    } catch (Exception e) {}

    int pickmeSize = pickmeService.size(nameSi, nameGu, keyword);
    System.out.println("게시글 총 개수 count : " + pickmeSize);

    int totalPageSize = pickmeSize / pageSize; 

    if ((pickmeSize % pageSize) > 0) {
      totalPageSize++;
    }

    try { 
      if (pageNo < 1 || pageNo > totalPageSize) {
        pageNo = 1;
      }
    } catch (Exception e) {}

    System.out.println("totalpageSize 는 " +totalPageSize );

    return 
        new ResultMap()
        .setStatus(SUCCESS)
        .setTotalListCount(pickmeSize)
        .setPageNo(pageNo)
        .setTotalPageSize(totalPageSize)
        .setData(pickmeService.list(pageSize, pageNo, nameSi, nameGu, keyword));
  }

  @RequestMapping("/pickme/mypagelist")
  public Object mypagelist (int pageSize, int pageNo, int memberNo, HttpSession session) {
    System.out.println(pageNo);
    try {
      if (pageSize < 3 || pageSize > 100) {
        pageSize = 3;
      }
    } catch (Exception e) {}

    int pickmeSize = pickmeService.mypageSize(memberNo);
    System.out.println("게시글 총 개수 count : " + pickmeSize);

    int totalPageSize = pickmeSize / pageSize; 

    if ((pickmeSize % pageSize) > 0) {
      totalPageSize++;
    }

    try { 
      if (pageNo < 1 || pageNo > totalPageSize) {
        pageNo = 1;
      }
    } catch (Exception e) {}

    System.out.println("totalpageSize 는 " +totalPageSize );

    return 
        new ResultMap()
        .setStatus(SUCCESS)
        .setTotalListCount(pickmeSize)
        .setPageNo(pageNo)
        .setTotalPageSize(totalPageSize)
        .setData(pickmeService.listbyMembNo(pageSize, pageNo, memberNo));
  }

  @RequestMapping("/pickme/add")
  public Object add(Pickme pickme, HttpSession session) {
    log.info("게시글 등록!"); // 운영자가 확인하기를 원하는 정보
    log.debug(pickme.toString()); // 개발자가 확인하기를 원하는 정보

    Member member = (Member) session.getAttribute("loginUser");
    pickme.setMember(member);
    pickmeService.add(pickme);

    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/pickme/get")
  public Object get(int no, HttpSession session) {
    Pickme pickme = pickmeService.get(no);
    if (pickme == null) {
      return new ResultMap().setStatus(FAIL).setData("해당 번호의 게시글이 없습니다.");
    }
    return new ResultMap().setStatus(SUCCESS).setData(pickme).setNo(no);
  }

  @RequestMapping("/pickme/update")
  public Object update(Pickme pickme, HttpSession session) {
    Member member = (Member) session.getAttribute("loginUser");
    pickme.setMember(member);

    int count = pickmeService.update(pickme);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 게시글 작성자가 아닙니다.");
    }
  }

  @RequestMapping("/pickme/delete")
  public Object delete(int no, HttpSession session) {
    Member member = (Member) session.getAttribute("loginUser");
    Pickme pickme = new Pickme();
    pickme.setNo(no);
    pickme.setMember(member);

    int count = pickmeService.delete(pickme);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 게시글 작성자가 아닙니다.");
    }
  }
}