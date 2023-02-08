package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bts.yomojomo.dao.JoinMemberDao;
import com.bts.yomojomo.domain.JoinMember;
import com.bts.yomojomo.service.JoinMemberService;

@Service
public class DefaultJoinMember implements JoinMemberService{

  @Autowired
  JoinMemberDao joinMemberDao;

  @Override // 경현 - 수정시 말해주세용
  public List<JoinMember> listbyGradeNo(JoinMember joinMember) {
    return joinMemberDao.findByGradeNo(joinMember);
  }

  @Override
  public List<JoinMember> grouplistByMno(JoinMember joinMember) {
    return joinMemberDao.findgrouplistByMno(joinMember);
  }

  @Override
  public List<JoinMember> grouplistByGno(JoinMember joinMember) {
    // TODO Auto-generated method stub
    return joinMemberDao.findgrouplistByGno(joinMember);
  }

  @Override
  public List<JoinMember> findGrade(JoinMember joinMember) {
    System.out.println(joinMember);
    return joinMemberDao.findGrade(joinMember);
  }

  @Override
  public int insertJoinGroupMember(JoinMember joinMember) {
    return joinMemberDao.insertJoinGroupMember(joinMember);
  }

  //이전코드
  @Override
  public List<JoinMember> list() {
    return joinMemberDao.findAll();
  }

  @Override
  public int add(JoinMember joinMember) {
    return joinMemberDao.insert(joinMember);
  }

  @Override
  public JoinMember get(int no) {
    JoinMember joinMember = joinMemberDao.findByNo(no);
    return joinMember;
  }

  @Transactional
  @Override
  public int update(JoinMember joinMember) {
    return joinMemberDao.update(joinMember);
  }

  @Transactional
  @Override
  public int delete(int no) {
    return joinMemberDao.delete(no);
  }

  @Override
  public int deleteMember(JoinMember joinMember) {
    return joinMemberDao.deleteMember(joinMember);
  }






}
