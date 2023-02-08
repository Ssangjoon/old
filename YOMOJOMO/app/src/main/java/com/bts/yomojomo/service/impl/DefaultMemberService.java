package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bts.yomojomo.dao.MemberDao;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.dto.MemberDto;
import com.bts.yomojomo.service.MemberService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DefaultMemberService implements MemberService {
  private static final String FROM_ADDRESS = "pwdmanager1108@gmail.com";

  @Autowired
  MemberDao memberDao;
  JavaMailSender mailSender;

  @Override
  @Transactional // 다음 메서드는 트랜잭션 안에서 실행하도록 설정한다.
  public int add(Member member) {
    memberDao.insert(member);
    memberDao.insertLocals(member.getNo(), member.getLocals());
    memberDao.insertPups(member.getNo(), member.getPups());
    return 1;
  }

  @Override
  public List<Member> listselect(int no, int cutno, String searchKeyword) {
    return memberDao.getBoardListSelect(no, cutno, searchKeyword);
  }

  @Override
  public int countSelect(String searchKeyword) {
    return memberDao.getBoardListSelectCount(searchKeyword);
  }

  @Override
  public Member get(int no) {
    return memberDao.findByNo(no);
  }
  
  @Override
  public Member get(String email, String password, String level) {
    return memberDao.findByEmailAndPassword(email, password, level);
  }


  @Override
  public Member get(String email) {
    return memberDao.findByEmail(email);
  }

  @Override
  public int update(int no, int status) {
    return memberDao.penalty(no, status);
  }

  @Override
  public int delete(int no) {
    return memberDao.membDelete(no);
  }
  
  @Override
  public Member find(String name, String tel) {
    return memberDao.findidByNameandTel(name, tel);
  }

  @Override
  public Member send(String email, String tel) {
    return memberDao.findpwdByEmailandTel(email, tel);
  }

  @Override
  public Member send(String email) {
    return memberDao.checkEmail(email);
  }

  @Override
  public MemberDto createMailandChangePassword(String email) {
    String str = getTempPassword();
    MemberDto dto = new MemberDto();
    dto.setAddress(email);
    dto.setTitle(email + "님의 Yomojomo 임시비밀번호 안내 이메일 입니다.");
    dto.setMessage("안녕하세요. Yomojomo 임시비밀번호 안내 관련 이메일 입니다." + "[" + email + "]" + "님의 임시 비밀번호는 " + str + " 입니다.");
    updatePassword(str, email);
    return dto;
  }

  @Override
  public void updatePassword(String str, String email) {

    memberDao.updatepwd(str, email);

  }

  @Override
  public String getTempPassword() {
    char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
        'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    String str = ""; 

    int idx = 0;
    for (int i = 0; i < 10; i++) {
      idx = (int) (charSet.length * Math.random());
      str += charSet[idx];
    }
    return str;
  }

  @Override
  public void mailSend(MemberDto memberDto) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(memberDto.getAddress());
    message.setFrom(DefaultMemberService.FROM_ADDRESS);
    message.setSubject(memberDto.getTitle());
    message.setText(memberDto.getMessage());
    mailSender.send(message);
  }


  //추가
  @Override
  public List<Member> list() {
    return memberDao.findAll();
  }



}
