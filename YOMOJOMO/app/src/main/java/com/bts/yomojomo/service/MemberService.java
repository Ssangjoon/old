package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.dto.MemberDto;

public interface MemberService {

  List<Member> listselect(int no, int cutno, String searchKeyword);

  int countSelect(String searchKeyword);
  
  int add(Member member);

  Member get(int no);
  
  Member get(String email);

  Member get(String email, String password, String level);

  int update(int no, int status);

  int delete(int no);
  
  Member find(String name, String tel);

  Member send(String email, String tel);

  Member send(String email);

  MemberDto createMailandChangePassword(String email);

  void updatePassword(String str, String userEmail);

  public String getTempPassword();

  public void mailSend(MemberDto memberDto);


  //추가
  List<Member> list();
}
