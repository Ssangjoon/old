package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bts.yomojomo.dao.AccountingDao;
import com.bts.yomojomo.domain.Accounting;
import com.bts.yomojomo.domain.AccountingCate;
import com.bts.yomojomo.service.AccountingService;

@Service
public class DefaultAccountingService implements AccountingService{

  @Autowired
  AccountingDao accountingDao;

  @Transactional
  @Override
  public int add(Accounting accounting, int qslength) {
    accountingDao.insert(accounting);
    accountingDao.insertActStatus(accounting.getAccountingNo(), accounting.getActStatus(), qslength);
    return 1;
  }

  @Override
  public List<Accounting> listByGroup(int pageSize, int pageNo, int groupNo, String actCate) {
    return accountingDao.findListByGroup(pageSize, ((pageNo - 1) * pageSize), groupNo, actCate);
  }

  @Override
  public Accounting get(int no) {
    return accountingDao.findByNo(no);
  }

  @Transactional
  @Override
  public int update(Accounting accounting, int qslength) {
    int count = accountingDao.update(accounting);
    if (count > 0) {
      accountingDao.deleteStatusByNo(accounting.getAccountingNo());
      accountingDao.insertActStatus(accounting.getAccountingNo(), accounting.getActStatus(), qslength);
    }
    return count;
  }

  @Transactional
  @Override
  public int delete(int accountingNo) {
    accountingDao.deleteStatusByNo(accountingNo);
    return accountingDao.delete(accountingNo);
  }

  @Override
  public List<AccountingCate> findCateList() {
    return accountingDao.cateListAll();
  }

  @Override
  public int size(int groupNo, String actCate) {
    return accountingDao.countAll(groupNo, actCate);
  }

  @Override
  public List<Accounting> list() {
    return accountingDao.findAll();
  }

}
