package com.bts.yomojomo.service;

import java.util.List;
import com.bts.yomojomo.domain.Accounting;
import com.bts.yomojomo.domain.AccountingCate;

public interface AccountingService {

  int add(Accounting accounting, int qslength);

  List<Accounting> listByGroup(int pageSize, int pageNo, int groupNo, String actCate);

  List<AccountingCate> findCateList();

  Accounting get(int no);


  int update(Accounting accounting, int qslength);

  int delete(int accountingNo);

  int size(int groupNo, String actCate);

  List<Accounting> list();

}
