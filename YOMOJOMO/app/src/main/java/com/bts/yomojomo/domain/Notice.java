package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Notice {
  int no;
  int mainCategoryNo;
  String cate;
  String title;
  String content;
  Date regDate;
  Mcate2 mCate;
}
