package com.bts.yomojomo.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ActiveLocal {
  int no; //act_local_no
  String nameSi;
  String nameGu;
}
