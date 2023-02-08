package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.InviteMemberDao;
import com.bts.yomojomo.domain.InviteMember;
import com.bts.yomojomo.service.InviteMemberService;

@Service
public class DefaultInviteMemberService implements InviteMemberService{

  @Autowired
  InviteMemberDao inviteMemberDao;

  @Override
  public List<InviteMember> memberlistByGroup(InviteMember invitemember) {
    return inviteMemberDao.findAll(invitemember);
  }


}
