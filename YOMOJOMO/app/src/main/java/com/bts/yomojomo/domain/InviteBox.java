package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class InviteBox {
  int inviteNo;                     //invite_no
  String title;                     //title
  String content;                   //content
  Boolean confirm;                  //confirm
  Date regDt;                       //reg_dt
  Member member;                    //Member 테이블(초대받는 사람) FK     - memb_no
  JoinMember joinMember;            //JoinMember 테이블(초대하는 사람)   - memb_no2, g_no
  //-> 초대하는 사람이 번호, 모임 번호 



}
