package com.bts.yomojomo.domain;

import java.sql.Date;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Member {
  int no; //memb_no
  String memberName;
  String passWord;
  String email;
  String tel;
  String postNo;
  String roadNameAddress;
  String baseAddress;
  String address;
  String membLevel;
  int unsubscribe;
  int status;
  Date stopDate;
  Date cDate;
  List<FinalActiveLocal> locals;
  List<FinalPurpose> pups;
}
