package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NoticeQuestion {
  int no;
  int mainCategoryNo;
  int queryNo;
  int memberNo;
  String title;
  String content;
  String path;
  Date queryDate;
  String answer;
  Date answerDate;
}
