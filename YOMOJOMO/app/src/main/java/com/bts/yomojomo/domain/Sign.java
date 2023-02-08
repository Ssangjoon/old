package com.bts.yomojomo.domain;

import java.sql.Date;
import lombok.Data;

@Data
public class Sign {
  int no; //memb_no
  String memberName;
  String passWord;
  String email;
  String tel;
  String postNo;
  String baseAddress;
  String address;
  String type;
  int unsubscribe;
  int status;
  Date stopDate;
}
