package com.bts.yomojomo.service;

import java.util.List;

import com.bts.yomojomo.domain.Notice;

public interface NoticeService {

  int add(Notice notice);

  List<Notice> list();

  public Notice get(int no);

  int update(Notice notice);

  int delete(int no);
  
 Notice select(int no);

  List<Notice> listselect(int no, int cutno, String searchKeyword);

  int countSelect(String searchKeyword);
}