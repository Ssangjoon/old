package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.MemberMidpointSelect;


public interface MemberMidpointSelectService {
  List<MemberMidpointSelect> memberListByGroupAndCalendar(MemberMidpointSelect memberSelectMidpoint);

  int add(MemberMidpointSelect memberMidpointSelect);

  int delete(MemberMidpointSelect memberMidpointSelect);
}
