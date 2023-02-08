package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.NoticeDao;
import com.bts.yomojomo.domain.Notice;
import com.bts.yomojomo.service.NoticeService;

@RestController
public class NoticeController {
  @Autowired // Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
//  NoticeDao noticeDao;
  NoticeService noticeService;

  @RequestMapping("/notice/add")
  public Object add(Notice notice) {
    noticeService.add(notice);
    return new ResultMap().setStatus(SUCCESS);
  }
//
//  @RequestMapping("/notice/get")
//  public Object get(int no) {
//    return noticeDao.findByNo(no);
//  }
//
//  @RequestMapping("/notice/list")
//  public Object list() {
//    return noticeDao.findAll();
//  }
//
  @RequestMapping("/notice/update")
  public Object update(Notice notice) {
    noticeService.update(notice);
    return new ResultMap().setStatus(SUCCESS);
  }
  
  @RequestMapping("/notice/delete")
  public Object delete(int no) {
    noticeService.delete(no);
    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/notice/select")
  public Object select(int no) {
    return noticeService.select(no);
  }

  @RequestMapping("/notice/listselect")
  public Object listselect(int no, int cutno, String searchKeyword) {
    int mNo = (no - 1) * cutno; // 넘어온 값에 -1로 데이터베이스에 맞춘다
    return noticeService.listselect(mNo, cutno, searchKeyword);
  }

  @RequestMapping("/notice/noticecountselect")
  public int getBoardListSelectCount(String searchKeyword) {
    return noticeService.countSelect(searchKeyword);
  }
}
