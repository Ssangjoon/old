package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.InviteMember;
import com.bts.yomojomo.service.InviteMemberService;

@RestController
public class InviteMemberController {

  @Autowired
  InviteMemberService inviteMemberService;

  @RequestMapping("/midpoint/member/list")
  public Object list(InviteMember invitemember) {

    return inviteMemberService.memberlistByGroup(invitemember);
  }
}
