package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.Report;

public interface ReportService {

  List<Report> listselect(int no, int cutno, String searchKeyword);

  int countSelect(String searchKeyword);

  Report get(int no);

  int update(int no, int status);

  int delete(int no);

  int add(Report report);
}
