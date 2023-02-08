package com.bts.yomojomo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bts.yomojomo.domain.JoinMember;

@Mapper 
public interface JoinMemberDao {


  List<JoinMember> findByGradeNo(JoinMember joinMember); //경현 - 수정시 말해주세요

  List<JoinMember> findgrouplistByMno(JoinMember joinMember); //경현 - 수정시 말해주세요

  List<JoinMember> findgrouplistByGno(JoinMember joinMember);

  List<JoinMember> findGrade(JoinMember joinMember); //상준 - 수정시 말해주세요

  int insertJoinGroupMember (JoinMember joinMember);


  //이전코드
  int countAll();

  List<JoinMember> findAll();

  int insert(JoinMember joinMember);

  JoinMember findByNo(int no);

  int update(JoinMember joinMember);

  int delete(int no);

  int deleteMember(JoinMember joinMember);
}
