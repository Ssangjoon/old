package com.bts.yomojomo.domain;

import java.sql.Date;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Accounting {
  int accountingNo;               //act_no
  String title;         //title
  int amount;           //amount
  int status;           //status
  Date registDate;      //reg_dt
  int actCateNo; //act_cate_no

  //외래키
  AccountingCate actCate;   //act_cate_no
  Group group;              //g_no

  //테이블 join하려고 가져온 domain class
  Member member;

  JoinMember joinMember;

  //AccountingStatus 배열
  List <AccountingStatus> actStatus;
}
