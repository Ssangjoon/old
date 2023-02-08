package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;
import lombok.experimental.Accessors;

//필드명 교체시 저한테 말해주세요 - 경현

@Data
@Accessors(chain = true)
public class Pickme {
  int no;                   //pm_b_no
  String title;             //title
  String content;           //content
  Date date;                //reg_dt
  int viewCnt;              //view_cnt
  ActiveLocal activeLocal;
  Purpose purpose;
  Member member;
}