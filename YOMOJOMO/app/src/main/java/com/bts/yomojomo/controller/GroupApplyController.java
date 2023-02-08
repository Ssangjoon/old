package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.GroupApplyForm;
import com.bts.yomojomo.service.GroupApplyService;

@RestController
public class GroupApplyController {

  @Autowired
  GroupApplyService groupApplyService;

  @RequestMapping("/mypage/group/sendApplyList")
  public Object sendApplyList(GroupApplyForm groupApplyForm) {
    System.out.println(groupApplyForm.toString());
    return groupApplyService.sendList(groupApplyForm);
  }

  @RequestMapping("/mypage/group/sendApplyDelet")
  public int sendApplyDelete(GroupApplyForm groupApplyForm) {
    return groupApplyService.sendDelete(groupApplyForm);
  }

  @RequestMapping("/mypage/group/reciveApplyList")
  public Object reviceApplyList(GroupApplyForm groupApplyForm) {
    System.out.println(groupApplyForm.toString());
    return groupApplyService.reciveList(groupApplyForm);
  }

  @RequestMapping("/mypage/group/joinGroup")
  public int joinGroup(GroupApplyForm groupApplyForm) {
    System.out.println(groupApplyForm.toString());
    return groupApplyService.add(groupApplyForm);
  }

  @RequestMapping("/mypage/group/reciveApplyDelete")
  public int reciveApplyDelete(GroupApplyForm groupApplyForm) {
    System.out.println(groupApplyForm.toString());
    return groupApplyService.reciveDelete(groupApplyForm);
  }

}
