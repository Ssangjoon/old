package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;

// 준호 작성
@Data
public class Destination {
  int no;
  String flat;
  String flng;
  String storeName;
  String locationId;
  Date regdt;
  int cnt;
  String categoryId;
  String placeURL;
}
