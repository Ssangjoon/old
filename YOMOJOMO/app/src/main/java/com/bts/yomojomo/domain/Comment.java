package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;

@Data
public class Comment {
  int no;
  int boardNo;
  String content;
  int groupNo;
  Date registDate;
  boolean status;
  Member writer;
}
