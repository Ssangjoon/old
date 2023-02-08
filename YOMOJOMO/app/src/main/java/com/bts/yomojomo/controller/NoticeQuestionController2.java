package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.NoticeQuestionDao2;
import com.bts.yomojomo.domain.NoticeQuestion2;

@RestController
public class NoticeQuestionController2 {
  @Autowired //Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
  NoticeQuestionDao2 noticeQuestionDao2;

  @RequestMapping("/noticeQuestion2/add")
  public Object add(NoticeQuestion2 noticequestion2) {
    return noticeQuestionDao2.insert(noticequestion2);
  }

  @RequestMapping("/noticeQuestion2/get")
  public Object get(int no) {
    return noticeQuestionDao2.findByNo(no);
  }

  @RequestMapping("/noticeQuestion2/list")
  public Object list() {
    return noticeQuestionDao2.findAll(); 
  }

  @RequestMapping("/noticeQuestion2/update")
  public Object update(NoticeQuestion2 noticequestion2) {
    return noticeQuestionDao2.update(noticequestion2);
  }

  @RequestMapping("/noticeQuestion2/delete")
  public Object delete(int no) {
    return noticeQuestionDao2.delete(no);
  }
}