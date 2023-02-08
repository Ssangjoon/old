package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApplyFixedAnswer {
  int groupNo; //g_no
  Member writer;
  Date applyDate;// appl_dt
  String content; //content
  Member master; //memb_no2
  int confirm;
}
