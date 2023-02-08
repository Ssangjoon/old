package com.bts.yomojomo.domain;

import java.sql.Date;
import java.util.List;
import lombok.Data;

@Data
public class Board {
  int no;
  int groupNo;
  int memberNo;
  Date registDate;
  String content;
  int viewCount;
  boolean status;
  Member writer;
  List<Comment> comments;
  String photo;
}
