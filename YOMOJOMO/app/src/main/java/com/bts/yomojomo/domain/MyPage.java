package com.bts.yomojomo.domain;

import java.util.List;
import lombok.Data;

@Data
public class MyPage {
  int no;
  String name;
  String pwd;
  String email;
  String tel;
  String postNo;
  String baseAddr;
  String addr;
  String roadNameAddr;
  String photo;
  boolean defaultImg;

  List <FinalActiveLocal> finalActiveLocal;
  List <FinalPurpose> finalPurpose;
  List <ActiveLocal> activeLocal;
  List <Purpose> purpose;
  List <Group> group;
  List <Board> board;
  Scrap scrap;
  List <NoticeQuestion> notice;
  QueryCate queryCate;
  Mcate2 mcate2;
}
