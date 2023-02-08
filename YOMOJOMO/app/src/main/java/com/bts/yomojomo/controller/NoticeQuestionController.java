package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.NoticeQuestionDao;
import com.bts.yomojomo.domain.NoticeQuestion;
import com.bts.yomojomo.service.NoticeQuestionService;

@RestController
public class NoticeQuestionController {
  @Autowired // Controller 객체를 만들 때 Dao인터페이스 구현체를 찾아 자동으로 주입한다.
//  NoticeQuestionDao noticeQuestionDao;
  NoticeQuestionService noticeQuestionService;
//
  @RequestMapping("/noticeQuestion/add")
  public Object add(NoticeQuestion noticequestion) {
    noticeQuestionService.add(noticequestion);
    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/noticeQuestion/update")
  public Object update(NoticeQuestion noticequestion) {
    noticeQuestionService.update(noticequestion);
    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/noticeQuestion/delete")
  public Object delete(int no) {
    noticeQuestionService.delete(no);
    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/noticeQuestion/updateAnswer")
  public Object updateAnswer(NoticeQuestion noticequestion) {
    noticeQuestionService.updateAnswer(noticequestion);
    return new ResultMap().setStatus(SUCCESS);
  }
  
  @RequestMapping("/noticeQuestion/select")
  public Object select(int no) {
    return noticeQuestionService.select(no);
  }

  @RequestMapping("/noticeQuestion/updatestatus")
  public Object updateStatus(int no, int status) {
    noticeQuestionService.updateStatus(no, status);

    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/noticeQuestion/listselect")
  public Object listselect(int no, int cutno, String searchKeyword) {
    
    System.out.println(cutno);
    System.out.println(searchKeyword);
    int mNo = (no - 1) * cutno; // 넘어온 값에 -1로 데이터베이스에 맞춘다
    System.out.println(mNo);
    return noticeQuestionService.listselect(mNo, cutno, searchKeyword);
  }

  @RequestMapping("/noticeQuestion/nqcountselect")
  public int getBoardListSelectCount(String searchKeyword) {
    String j = searchKeyword;
    System.out.println(j);
    return noticeQuestionService.countSelect(j);
  }
}
