package com.bts.yomojomo.controller;

import static com.bts.yomojomo.controller.ResultMap.SUCCESS;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.domain.Member;
import com.bts.yomojomo.domain.Report;
import com.bts.yomojomo.service.ReportService;

@RestController
public class reportController {

  @Autowired
  ReportService reportService;


  @RequestMapping("/report/add")
  public Object add(Report report, HttpSession session) {
    Member member = (Member) session.getAttribute("loginUser");
    report.setMembNo(member.getNo());
    System.out.println(report);
    return new ResultMap().setStatus(SUCCESS).setData(reportService.add(report));
  }

  @RequestMapping("/report/reportcountselect")
  public int getBoardListSelectCount(String searchKeyword) {
    return reportService.countSelect(searchKeyword);
  }

  @RequestMapping("/report/listselect")
  public Object listselect(int no, int cutno, String searchKeyword) {
    int mNo = (no - 1) * cutno; // 넘어온 값에 -1로 데이터베이스에 맞춘다
    return reportService.listselect(mNo, cutno, searchKeyword);
  }

  @RequestMapping("/report/get")
  public Report get(int no) {
    return reportService.get(no);
  }

  @RequestMapping("/report/update")
  public Object update(int no, int valid) {
    System.out.println("no::::" + no);
    System.out.println("status::::" + valid);

    reportService.update(no, valid);

    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/report/delete")
  public Object delete(int no) {
    System.out.println("no::::" + no);

    reportService.delete(no);

    return new ResultMap().setStatus(SUCCESS);
  }
}