package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class FAQ {
  int no;
  int maincateno;
  int queryno;
  String title;
  String content;
  String path;
  Date regdt;

  //외래키
  QueryCate queryCate;
  
}
