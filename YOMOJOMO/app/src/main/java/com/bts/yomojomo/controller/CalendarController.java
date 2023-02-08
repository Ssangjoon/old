package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.FAIL;
import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.Calendar;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.service.CalendarSerivce;

@RestController
public class CalendarController {

  private static final Logger log = LoggerFactory.getLogger(CalendarController.class);

  @Autowired
  CalendarSerivce calendarservice;

  @RequestMapping("/calendar/list")//캘린더 전체 리스트 조회
  public Object list() {
    log.info("캘린더 목록 조회");
    return new ResultMap().setStatus(SUCCESS).setData(calendarservice.list());
  }

  @RequestMapping("/calendar/listbygroup") //소모임 번호로 리스트 받기
  public Object listByGroup(Calendar calendar, HttpSession session) {
    log.info("모임별 캘린더 목록 조회");

    Member member = (Member) session.getAttribute("loginUser");
    calendar.setMember(member);

    return new ResultMap().setStatus(SUCCESS).setData(calendarservice.listByGroup(calendar));
  }





  @RequestMapping("/calendar/add")
  public Object add(Calendar calendar, HttpSession session) {
    log.info("일정 등록!"); // 운영자가 확인하기를 원하는 정보
    log.debug(calendar.toString()); // 개발자가 확인하기를 원하는 정보

    Member member = (Member) session.getAttribute("loginUser");
    calendar.setMember(member);
    calendarservice.add(calendar);

    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/calendar/get")
  public Object get(int no, HttpSession session) {
    Calendar calendar = calendarservice.get(no);
    if (calendar == null) {
      return new ResultMap().setStatus(FAIL).setData("해당 번호의 게시글이 없습니다.");
    }
    return new ResultMap().setStatus(SUCCESS).setData(calendar);
  }

  @RequestMapping("/calendar/update")
  public Object update(Calendar calendar, HttpSession session) {
    System.out.println(calendar);
    Member member = (Member) session.getAttribute("loginUser");
    calendar.setMember(member);

    int count = calendarservice.update(calendar);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 게시글 작성자가 아닙니다.");
    }
  }

  @RequestMapping("/calendar/delete")
  public Object delete(int no, HttpSession session) {
    Member member = (Member) session.getAttribute("loginUser");
    Calendar calendar = new Calendar();
    calendar.setNo(no);
    calendar.setMember(member);

    int count = calendarservice.delete(calendar);

    if (count == 1) {
      return new ResultMap().setStatus(SUCCESS);
    } else {
      return new ResultMap().setStatus(FAIL).setData("게시글 번호가 유효하지 않거나 게시글 작성자가 아닙니다.");
    }
  }
}
