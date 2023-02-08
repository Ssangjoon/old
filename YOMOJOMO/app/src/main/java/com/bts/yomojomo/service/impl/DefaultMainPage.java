package com.bts.yomojomo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bts.yomojomo.dao.MainPageDao;
import com.bts.yomojomo.domain.MainPage;
import com.bts.yomojomo.service.MainPageService;

@Service
public class DefaultMainPage implements MainPageService {

  @Autowired
  MainPageDao mainPageDao;

  @Override
  public List<MainPage> nonmemberGroupList() {
    return mainPageDao.nonMemberGroupList();
  }

  @Override
  public List<MainPage> memberGroupList(int no) {
    return mainPageDao.memberGroupList(no);
  }

}
