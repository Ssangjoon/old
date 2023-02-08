package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.PurposeDao;
import com.bts.yomojomo.domain.Purpose;
import com.bts.yomojomo.service.PurposeService;

@Service
public class DefaultPurposeService implements PurposeService{

  @Autowired
  PurposeDao purposeDao;

  @Override
  public List<Purpose> list() {
    return purposeDao.findAll();
  }

  @Override
  public Purpose get(int no) {
    Purpose purpose = purposeDao.findByNo(no);
    return purpose;
  }

}
