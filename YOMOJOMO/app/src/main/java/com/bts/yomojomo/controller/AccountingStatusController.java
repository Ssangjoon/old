package com.bts.yomojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bts.yomojomo.dao.AccountingStatusDao;
import com.bts.yomojomo.domain.AccountingStatus;


@RestController
public class AccountingStatusController {
  @Autowired
  AccountingStatusDao accountingStatusDao;

  @RequestMapping("/accountingstatus/add")
  public Object add(AccountingStatus accountingStatus) {
    return accountingStatusDao.insert(accountingStatus);
  }

  @RequestMapping("/accountingstatus/get")
  public Object get(int actno) {
    return accountingStatusDao.findByNo(actno);
  }

  @RequestMapping("/accountingstatus/list")
  public Object list() {
    return accountingStatusDao.findAll(); 
  }

  @RequestMapping("/accountingstatus/update")
  public Object update(AccountingStatus accountingStatus) {
    return accountingStatusDao.update(accountingStatus);
  }

  @RequestMapping("/accountingstatus/delete")
  public Object delete(int actno) {
    return accountingStatusDao.delete(actno);
  }
}
