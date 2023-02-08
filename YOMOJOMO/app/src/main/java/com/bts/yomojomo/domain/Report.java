package com.bts.yomojomo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Report {
  int rptNo; 
  String title;
  String content;
  int valid;
  int membNo;
  int rptCateNo;
  int reported;
  ReportCate reportCate;
  Member member;
  Member reportedMember;
}
