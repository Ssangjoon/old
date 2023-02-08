package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Calendar {
  int no;
  String name;
  String content;
  String resulturl;
  Date startDt;
  Date endDt;
  JoinMember joinMemb;
  Member member;
}
