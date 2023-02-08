package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.JoinMember;

public interface JoinMemberService {



  List<JoinMember> listbyGradeNo(JoinMember joinMember); //경현 - 수정시 말해주세요

  List<JoinMember> grouplistByMno(JoinMember joinMember); //경현 - 수정시 말해주세요

  List<JoinMember> grouplistByGno(JoinMember joinMember); //경현 - 수정시 말해주세요

  List<JoinMember> findGrade(JoinMember joinMember); //상준 - 수정시 말해주세요


  int insertJoinGroupMember(JoinMember joinMember);

  //이전코드
  List<JoinMember> list();

  int add(JoinMember joinMember);

  JoinMember get(int no);

  int update(JoinMember joinMember);

  int delete(int no);

  int deleteMember(JoinMember joinMember);

}
