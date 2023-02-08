package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.ReportDao;
import com.bts.yomojomo.domain.Report;
import com.bts.yomojomo.service.ReportService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DefaultReportService implements ReportService {

  @Autowired
  ReportDao reportDao;
  JavaMailSender mailSender;

  @Override
  public List<Report> listselect(int no, int cutno, String searchKeyword) {
    return reportDao.getBoardListSelect(no, cutno, searchKeyword);
  }

  @Override
  public int countSelect(String searchKeyword) {
    return reportDao.getBoardListSelectCount(searchKeyword);
  }

  @Override
  public Report get(int no) {
    return reportDao.findByNo(no);
  }

  @Override
  public int update(int no, int vaild) {
    return reportDao.valid(no, vaild);
  }

  @Override
  public int delete(int no) {
    return reportDao.reportDelete(no);
  }

  @Override
  public int add(Report report) {
    return reportDao.insert(report);
  }
}
