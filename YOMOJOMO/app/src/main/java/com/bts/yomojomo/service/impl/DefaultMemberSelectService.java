package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.MemberMidpointSelectDao;
import com.bts.yomojomo.domain.MemberMidpointSelect;
import com.bts.yomojomo.service.MemberMidpointSelectService;

@Service
public class DefaultMemberSelectService implements MemberMidpointSelectService {

  @Autowired
  MemberMidpointSelectDao memberMidpointSelectDao;

  @Override
  public List<MemberMidpointSelect> memberListByGroupAndCalendar(
      MemberMidpointSelect memberSelectMidpoint) {
    return memberMidpointSelectDao.findAll(memberSelectMidpoint);
  }

  @Override
  public int add(MemberMidpointSelect memberMidpointSelect) {
    return memberMidpointSelectDao.insert(memberMidpointSelect);
  }

  @Override
  public int delete(MemberMidpointSelect memberMidpointSelect) {
    return memberMidpointSelectDao.delete(memberMidpointSelect);
  }


}
