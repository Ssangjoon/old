package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.domain.MemberMidpointSelect;
import com.bts.yomojomo.service.MemberMidpointSelectService;

@RestController
public class MemberMidpointSelectController {

  //log를 출력하는 도구 준비
  private static final Logger log = LoggerFactory.getLogger(MemberMidpointSelectController.class);

  @Autowired 
  MemberMidpointSelectService memberMidpointSelectService;

  @RequestMapping("/midpoint/member/calendar/list")
  public Object list(MemberMidpointSelect memberMidpointSelect) {
    return memberMidpointSelectService.memberListByGroupAndCalendar(memberMidpointSelect);
  }

  @RequestMapping("/midpoint/member/calendar/add")
  public Object add(MemberMidpointSelect memberMidpointSelect, HttpSession session) {
    log.info("맴버위치 장소 입력"); // 운영자가 확인하기를 원하는 정보
    log.debug(memberMidpointSelect.toString()); // 개발자가 확인하기를 원하는 정보

    Member member = (Member) session.getAttribute("loginUser");
    memberMidpointSelect.setMember(member);
    memberMidpointSelectService.add(memberMidpointSelect);
    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/midpoint/member/calendar/delete")
  public Object delete(MemberMidpointSelect memberMidpointSelect, HttpSession session) {

    Member member = (Member) session.getAttribute("loginUser");
    memberMidpointSelect.setMember(member);
    memberMidpointSelectService.delete(memberMidpointSelect);
    return new ResultMap().setStatus(SUCCESS);
  }



}